package ru.te3ka.getmyipaddress

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ru.te3ka.getmyipaddress.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myIpViewModel: MyIpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myIpViewModel.lastIp.observe(this, Observer { ipAddress ->
            binding.textViewMyIpAddress.text = ipAddress?.myIp ?: "No IP Address"
        })

        binding.buttonRefresh.setOnClickListener {
            myIpViewModel.fetchIpAddress()
        }
    }
}