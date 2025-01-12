package com.example.privatbankapi // Заміни на фактичний пакет твого проєкту

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Підключення activity_main.xml

        // Ініціалізація RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) // Встановлюємо вертикальний список
        recyclerView.adapter = CurrencyAdapter(emptyList()) // Тимчасовий порожній список
    }
}
