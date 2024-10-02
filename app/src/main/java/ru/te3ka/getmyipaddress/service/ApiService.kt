package ru.te3ka.getmyipaddress.service

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("d4e2bt6jba6cmiekqmsv")
    fun getIpAddress(): Call<String>
}