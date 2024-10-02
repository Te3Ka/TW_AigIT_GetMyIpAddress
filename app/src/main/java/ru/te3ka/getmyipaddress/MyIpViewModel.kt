package ru.te3ka.getmyipaddress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.te3ka.getmyipaddress.data.MyIpAddress
import ru.te3ka.getmyipaddress.db.MyIpDatabase
import ru.te3ka.getmyipaddress.repository.MyIpRepository
import ru.te3ka.getmyipaddress.service.ApiService
import ru.te3ka.getmyipaddress.service.RetrofitInstance

class MyIpViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MyIpRepository
    val lastIp: Flow<MyIpAddress?>

    init {
        val MyIpAddressDao = MyIpDatabase.getDatabase(application).MyIpAddressDao()
        repository = MyIpRepository(MyIpAddressDao)
        lastIp = repository.lastIp
    }

    fun fetchAndSaveIp(apiService: ApiService) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getIpAddress().execute()
                if (response.isSuccessful) {
                    val ip = response.body()?.myip ?: "No IP"
                    repository.insert(MyIpAddress(myIp = ip))
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}