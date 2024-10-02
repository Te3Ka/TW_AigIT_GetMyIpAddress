package ru.te3ka.getmyipaddress.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.te3ka.getmyipaddress.data.MyIpAddress

@Dao
interface MyIpAddressDao {
    @Insert
    suspend fun insert(myIpAddress: MyIpAddress)

    @Query("SELECT * FROM ip_table ORDER BY id DESC LIMIT 1")
    fun getLastMyIpAddress(): Flow<MyIpAddress?>
}