package ru.te3ka.getmyipaddress.repository

import androidx.lifecycle.LiveData
import ru.te3ka.getmyipaddress.dao.MyIpAddressDao
import ru.te3ka.getmyipaddress.data.MyIpAddress

class MyIpRepository(private val myIpAddressDao: MyIpAddressDao) {
    val lastIp: LiveData<MyIpAddress?> = myIpAddressDao.getLastMyIpAddress()

    suspend fun insert(myIpAddress: MyIpAddress) {
        myIpAddressDao.insert(myIpAddress)
    }
}