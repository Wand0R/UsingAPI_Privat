package com.example.privatbankapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// ОБОВ'ЯЗКОВО імпортуємо com.example.privatbankapi.R
import com.example.privatbankapi.R

class CurrencyAdapter(
    private var currencyList: List<CurrencyRate>
) : RecyclerView.Adapter<CurrencyAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Використовуємо наш клас-«обгортку» з findViewById
        private val holder = CurrencyViewHolder(view)

        fun bind(currency: CurrencyRate) {
            holder.tvCurrency.text = currency.ccy ?: "N/A"
            holder.tvBaseCurrency.text = "Base: ${currency.base_ccy ?: "N/A"}"
            holder.tvBuy.text = "Buy: ${currency.buy ?: "N/A"}"
            holder.tvSale.text = "Sale: ${currency.sale ?: "N/A"}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int = currencyList.size

    fun updateData(newCurrencyList: List<CurrencyRate>) {
        this.currencyList = newCurrencyList
        notifyDataSetChanged()
    }
}


