package com.example.currencyconversion.API

import com.example.currencyconversion.models.CurrencyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("latest.json")
    suspend fun getCurrency(
        @Query("app_id") appId: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Call<CurrencyData>
}