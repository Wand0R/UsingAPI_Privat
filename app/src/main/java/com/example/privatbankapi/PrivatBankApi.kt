package com.example.privatbankapi

import retrofit2.http.GET
import retrofit2.http.Query

// Виклик API
data class ExchangeRate(
    val currency: String?,
    val baseCurrency: String,
    val saleRate: Double?,
    val purchaseRate: Double?
)

interface PrivatBankApi {
    @GET("exchange_rates?json")
    suspend fun getCurrencyArchive(
        @Query("date") date: String
    ): ResponseData
}

data class ResponseData(
    val date: String,
    val exchangeRate: List<ExchangeRate>
)
