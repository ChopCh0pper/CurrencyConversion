package com.example.currencyconversion.API

import com.example.currencyconversion.models.CurrencyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("v6/{apiKey}/{request}/{baseCode}/{targetCode}/{amount}")
    suspend fun getCurrency(
        @Path("apiKey") apiKey: String,
        @Path("request") request: String,
        @Path("baseCode") baseCode: String,
        @Path("targetCode") targetCode: String,
        @Path("amount") amount: String
    ): Response<CurrencyData>
}