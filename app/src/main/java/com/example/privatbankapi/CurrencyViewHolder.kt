package com.example.privatbankapi

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvCurrency: TextView = itemView.findViewById(R.id.tvCurrency)
    val tvBaseCurrency: TextView = itemView.findViewById(R.id.tvBaseCurrency)
    val tvBuy: TextView = itemView.findViewById(R.id.tvBuy)
    val tvSale: TextView = itemView.findViewById(R.id.tvSale)
}
