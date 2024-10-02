package ru.te3ka.getmyipaddress.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.te3ka.getmyipaddress.dao.MyIpAddressDao
import ru.te3ka.getmyipaddress.data.MyIpAddress

@Database(entities = [MyIpAddress::class], version = 1)
abstract class MyIpDatabase : RoomDatabase() {
    abstract fun MyIpAddressDao(): MyIpAddressDao

    companion object {
        @Volatile
        private var INSTANCE: MyIpDatabase? = null

        fun getDatabase(context: Context): MyIpDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyIpDatabase::class.java,
                    "my_ip_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}