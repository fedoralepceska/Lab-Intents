package com.example.lab_intents

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_intents.adapters.ExampleViewAdapter

class ListViewActivity: AppCompatActivity() {

    private lateinit var listView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView = findViewById(R.id.list_view)

        listView.adapter = ExampleViewAdapter(loadData())
    }

    private fun loadData(): MutableList<String> {
        val bundle: Bundle? = intent.extras
        listView = bundle?.get("displayList") as RecyclerView
        return mutableListOf(listView.toString())
    }
}