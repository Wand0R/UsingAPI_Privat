package com.example.privatbankapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyViewModel : ViewModel() {

    // LiveData для списку курсів валют
    private val _currencyRates = MutableLiveData<List<ExchangeRate>>()
    val currencyRates: LiveData<List<ExchangeRate>> get() = _currencyRates

    // Ініціалізація Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.privatbank.ua/p24api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PrivatBankApi::class.java)

    // Завантаження даних за конкретну дату
    fun fetchCurrencyRates(date: String) {
        viewModelScope.launch {
            try {
                val response = api.getCurrencyArchive(date = date)
                _currencyRates.value = response.exchangeRate.filter { it.currency != null }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
