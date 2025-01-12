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

data class CurrencyArchiveResponse(
    val date: String,
    val exchangeRate: List<ExchangeRate>
)


interface PrivatBankApi {
    @GET("exchange_rates")
    suspend fun getCurrencyArchive(
        @Query("json") json: Boolean = true,
        @Query("date") date: String // Date "dd.MM.yyyy"
    ): CurrencyArchiveResponse
}
