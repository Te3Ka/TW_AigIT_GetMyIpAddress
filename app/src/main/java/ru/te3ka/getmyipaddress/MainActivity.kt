package ru.te3ka.getmyipaddress

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
                binding.textViewMyIpAddress.text = ip?.myIp ?: "No IP Address"
            }
        }

        binding.buttonRefresh.setOnClickListener {
            binding.textViewMyIpAddress.text = "Refreshing"
            myIpViewModel.fetchAndSaveIp(RetrofitInstance.api)
        }
    }
}