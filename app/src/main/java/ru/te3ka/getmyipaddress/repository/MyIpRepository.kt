package ru.te3ka.getmyipaddress.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.te3ka.getmyipaddress.dao.MyIpAddressDao
import ru.te3ka.getmyipaddress.data.MyIpAddress

class MyIpRepository(private val myIpAddressDao: MyIpAddressDao) {
    val lastIp: Flow<MyIpAddress?> = myIpAddressDao.getLastMyIpAddress()

    suspend fun insert(myIpAddress: MyIpAddress) {
        myIpAddressDao.insert(myIpAddress)
    }
}