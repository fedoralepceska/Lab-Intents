package com.example.lab_intents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ExplicitActivity: AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var btnApprove: Button
    private lateinit var btnDismiss: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        editText = findViewById(R.id.editText)
        btnApprove = findViewById(R.id.btnApprove)
        btnDismiss = findViewById(R.id.btnDismiss)

        val mainActivityIntent = Intent(this, MainActivity::class.java);
        btnApprove.setOnClickListener {
            mainActivityIntent.putExtra("textInput", editText.text.toString());
            startActivity(mainActivityIntent);
        }

        btnDismiss.setOnClickListener {
            finish()
        }
    }
}