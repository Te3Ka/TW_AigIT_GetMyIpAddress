package ru.te3ka.getmyipaddress

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.te3ka.getmyipaddress.databinding.ActivityMainBinding
import ru.te3ka.getmyipaddress.service.RetrofitInstance
import kotlin.random.Random


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
                binding.progressBarCircleLoadMyIp.visibility = View.GONE
            }
        }

        binding.buttonRefresh.setOnClickListener {
            binding.progressBarCircleLoadMyIp.visibility = View.VISIBLE
            binding.textViewMyIpAddress.text = getString(R.string.refreshing)

            lifecycleScope.launch {
                val randomDeleay = Random.nextLong(200, 5000)
                delay(randomDeleay)
                myIpViewModel.fetchAndSaveIp(RetrofitInstance.api)
            }
        }
    }
}