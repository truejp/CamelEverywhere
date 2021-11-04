package com.example.kamelrechner
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val res = intent.getStringExtra("erg")
        var i = Log.i("Result received","Value for Result: $res")
        val label = findViewById<TextView>(R.id.textView8)
        label.text = "Du bist $res"

        val goBtn = findViewById<Button>(R.id.show_info)
        goBtn.setOnClickListener {
            val intent = Intent(this@Result, about::class.java)
            intent.putExtra("erg", res)
            startActivity(intent)
        }

        val closeBtn = findViewById<Button>(R.id.res_close_btn)
        closeBtn.setOnClickListener {
            val intent = Intent(this@Result, Calculator::class.java)
            startActivity(intent)
        }
    }
}