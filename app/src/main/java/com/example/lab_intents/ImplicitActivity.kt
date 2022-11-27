package com.example.lab_intents

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ImplicitActivity: AppCompatActivity() {

    private lateinit var displayList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        displayList = findViewById(R.id.displayList)

        //get the extra defined in MainActivity
        val bundle: Bundle? = intent.extras
        displayList.text = bundle?.get("displayList").toString()
    }
}
