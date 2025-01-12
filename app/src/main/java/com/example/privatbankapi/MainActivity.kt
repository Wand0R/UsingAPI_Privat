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
        val adapter = CurrencyAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // ViewModel
        viewModel.currencyRates.observe(this) { rates ->
            adapter.updateData(rates.map { rate ->
                CurrencyRate(
                    ccy = rate.currency ?: "",
                    base_ccy = rate.baseCurrency,
                    buy = rate.purchaseRate?.toString() ?: "N/A",
                    sale = rate.saleRate?.toString() ?: "N/A"
                )
            })
        }

        // Currency by date
        val date = "01.12.2014" // date example
        viewModel.fetchCurrencyRates(date)
    }
}
