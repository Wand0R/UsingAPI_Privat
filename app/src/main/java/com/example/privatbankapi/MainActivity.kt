package com.example.privatbankapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = CurrencyAdapter(emptyList()) // Підключення адаптера
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // ViewModel
        viewModel.currencyRates.observe(this) { rates ->
            if (rates != null) {
                val formattedRates = rates.map { rate ->
                    CurrencyRate(
                        ccy = rate.currency ?: "Unknown",
                        base_ccy = rate.baseCurrency,
                        buy = rate.purchaseRate?.toString() ?: "N/A",
                        sale = rate.saleRate?.toString() ?: "N/A"
                    )
                }
                adapter.updateData(formattedRates) // Оновлюємо дані в адаптері
            }
        }

        // Currency by date
        val date = "01.12.2014" // Date example
        viewModel.fetchCurrencyRates(date)
    }
}
