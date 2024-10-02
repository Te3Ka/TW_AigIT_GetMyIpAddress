package ru.te3ka.getmyipaddress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.te3ka.getmyipaddress.data.MyIpAddress
import ru.te3ka.getmyipaddress.db.MyIpDatabase
import ru.te3ka.getmyipaddress.repository.MyIpRepository
import ru.te3ka.getmyipaddress.service.RetrofitInstance

class MyIpViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MyIpRepository
    val lastIp: LiveData<MyIpAddress?>

    init {
        val MyIpAddressDao = MyIpDatabase.getDatabase(application).MyIpAddressDao()
        repository = MyIpRepository(MyIpAddressDao)
        lastIp = repository.lastIp
    }

    fun insert(myIpAddress: MyIpAddress) = viewModelScope.launch {
        repository.insert(myIpAddress)
    }

    fun fetchIpAddress() {
        viewModelScope.launch(Dispatchers.IO) {
            val call = RetrofitInstance.api.getIpAddress()

            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        val ip = response.body() ?: "No IP"
                        insert(MyIpAddress(myIp = ip))
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}