package com.example.helloworldapp.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST(".")
    fun sendWifiData(@Body data: WifiData): Call<WifiData>
}