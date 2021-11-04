package com.example.kamelrechner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class about : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val res = intent.getStringExtra("erg")
        val goBtn = findViewById(R.id.info_close_btn) as Button
        goBtn.setOnClickListener {
            val intent = Intent(this@about, Result::class.java)
            intent.putExtra("erg", res)
            startActivity(intent)
        }
    }
}