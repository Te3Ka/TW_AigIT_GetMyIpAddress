package ru.te3ka.getmyipaddress.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Таблица базы данных для хранения значения текущего IP-адреса
 */
@Entity(tableName = "ip_table")
data class MyIpAddress(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val myIp: String
)
