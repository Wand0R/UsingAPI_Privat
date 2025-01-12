package com.example.privatbankapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.TextView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val viewModel: CurrencyViewModel by lazy {
        ViewModelProvider(this)[CurrencyViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ініціалізація RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = CurrencyAdapter(emptyList()) // Підключення адаптера
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Ініціалізація кнопки вибору дати
        val btnSelectDate: Button = findViewById(R.id.btnSelectDate)
        val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)

        // Поточна вибрана дата
        var selectedDate = "01.12.2014" // За замовчуванням

        btnSelectDate.setOnClickListener {
            showDatePickerDialog { date ->
                selectedDate = date
                tvSelectedDate.text = "Selected Date: $date"
                viewModel.fetchCurrencyRates(date) // Завантаження даних за вибрану дату
            }
        }

        // Спостерігач для ViewModel
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

        // Завантаження даних за замовчуванням
        viewModel.fetchCurrencyRates(selectedDate)
    }

    // Метод для відкриття DatePickerDialog
    private fun showDatePickerDialog(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Формат дати: dd.MM.yyyy
            val date = String.format("%02d.%02d.%d", selectedDay, selectedMonth + 1, selectedYear)
            onDateSelected(date)
        }, year, month, day)

        datePickerDialog.show()
    }
}
