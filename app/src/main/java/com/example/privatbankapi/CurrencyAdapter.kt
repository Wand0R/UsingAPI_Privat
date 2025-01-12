package com.example.privatbankapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CurrencyAdapter(private var currencyList: List<CurrencyRate>) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {


    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ccyTextView: TextView = view.findViewById(R.id.ccyTextView)
        val baseCcyTextView: TextView = view.findViewById(R.id.baseCcyTextView)
        val buyTextView: TextView = view.findViewById(R.id.buyTextView)
        val saleTextView: TextView = view.findViewById(R.id.saleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencyList[position]
        holder.ccyTextView.text = currency.ccy
        holder.baseCcyTextView.text = "Base: ${currency.base_ccy}"
        holder.buyTextView.text = "Buy: ${currency.buy}"
        holder.saleTextView.text = "Sale: ${currency.sale}"
    }

    override fun getItemCount(): Int = currencyList.size

    // Оновлення даних у списку
    fun updateData(newList: List<CurrencyRate>) {
        currencyList = newList
        notifyDataSetChanged()
    }
}
