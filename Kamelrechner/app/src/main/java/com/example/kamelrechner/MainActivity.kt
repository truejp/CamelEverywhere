package com.example.kamelrechner
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log;
import com.example.kamelrechner.CamelDB;


class MainActivity : AppCompatActivity() {
    private var dataSource: CamelDBDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goBtn = findViewById<Button>(R.id.startButton)
        goBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Calculator::class.java)
            startActivity(intent)
        }

        //CamelDB testMemo = new CamelDB("Peter", 23, 70, 1,1,1,1,1,1,1);
        //Log.d("New DB Entry found", "Inhalt der Testmemo: ");

        //dataSource = new CamelDBHelper(this);
    }
}