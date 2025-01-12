package com.example.privatbankapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyViewModel : ViewModel() {

    // LiveData
    private val _currencyRates = MutableLiveData<List<ExchangeRate>>()
    val currencyRates: LiveData<List<ExchangeRate>> get() = _currencyRates

    // Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.privatbank.ua/p24api/")
        .addConverterFactory(GsonConverterFactory.create()) // JSON to kt
        .build()

    private val api = retrofit.create(PrivatBankApi::class.java)

    // Currency by date
    fun fetchCurrencyRates(date: String) {
        viewModelScope.launch {
            try {
                val response = api.getCurrencyArchive(date = date) // API
                _currencyRates.value = response.exchangeRate.filter { it.currency != null }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
