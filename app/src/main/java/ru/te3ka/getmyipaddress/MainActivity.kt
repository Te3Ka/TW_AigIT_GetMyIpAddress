package ru.te3ka.getmyipaddress

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.te3ka.getmyipaddress.databinding.ActivityMainBinding
import ru.te3ka.getmyipaddress.service.RetrofitInstance


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myIpViewModel: MyIpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            myIpViewModel.lastIp.collectLatest { ip ->
                binding.textViewMyIpAddress.text = ip?.myIp ?: getString(
                    R.string.no_ip_address)
            }
        }

        binding.buttonRefresh.setOnClickListener {
            binding.textViewMyIpAddress.text = getString(R.string.refreshing)
            myIpViewModel.fetchAndSaveIp(RetrofitInstance.api)
        }
    }
}