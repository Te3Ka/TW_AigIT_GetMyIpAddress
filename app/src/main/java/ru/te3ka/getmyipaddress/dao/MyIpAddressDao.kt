package ru.te3ka.getmyipaddress.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.te3ka.getmyipaddress.data.MyIpAddress

@Dao
interface MyIpAddressDao {
    @Insert
    suspend fun insert(myIpAddress: MyIpAddress)

    @Query("SELECT * FROM ip_table ORDER BY id DESC LIMIT 1")
    fun getLastMyIpAddress(): LiveData<MyIpAddress?>
}