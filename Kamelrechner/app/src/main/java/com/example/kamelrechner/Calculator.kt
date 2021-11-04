package com.example.kamelrechner
import android.annotation.SuppressLint
import android.view.View
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.widget.*
import org.w3c.dom.Text
import java.text.NumberFormat

class Calculator : AppCompatActivity() {
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    private lateinit var button: Button
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        val calcBtn = findViewById<Button>(R.id.calculate)
        calcBtn.setOnClickListener {
            //Calculate value

            //get values
            val breast = findViewById<SeekBar>(R.id.breastsize_input).progress
            val eye = findViewById<SeekBar>(R.id.eyecolor_input).progress
            val hair = findViewById<SeekBar>(R.id.haircolor_input).progress
            val body = findViewById<SeekBar>(R.id.body_input).progress
            val name = findViewById<TextView>(R.id.name_input).text
            val ageHelper = findViewById<TextView>(R.id.age_input).text
            val weightHelper = findViewById<TextView>(R.id.weigth_input).text

            //check if any value is missing

            if (ageHelper.trim().isEmpty() || weightHelper.trim().isEmpty() || name.trim()
                    .isEmpty()
            ) {
                Toast.makeText(this, "Bitte prüfe deine Eingaben!", Toast.LENGTH_SHORT).show()
                var i = Log.i("Error", "Missing values")
            } else {
                val age: Int = Integer.parseInt(ageHelper.toString())
                val weight: Int = Integer.parseInt(weightHelper.toString())
                var i = Log.i("No Error", "No missing values")
                //Calculate Gender Bonus
                radioGroup = findViewById(R.id.gender_input)
                val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
                radioButton = findViewById(intSelectButton)
                var gender = 10
                if (radioButton.text == "Weiblich") {
                    gender = 5
                }

                //Report all Values to LogCat
                i = Log.i("Object Found", "Value from Body: $body")
                i = Log.i("Object Found", "Value from Eye: $eye")
                i = Log.i("Object Found", "Value from Breast: $breast")
                i = Log.i("Object Found", "Value from Hair: $hair")
                i = Log.i("Object Found", "Value from Gender: $gender")
                i = Log.i("Object Found", "Value from Name: $name")
                i = Log.i("Object Found", "Value from Age: $age")
                i = Log.i("Object Found", "Value from Weight: $weight")

                //calculate camels

                var camels =
                    (body + eye + breast + hair) * 2 + (1 / 200 * (weight - 65) * (weight - 65) + 10) + gender + (1 / 200 * (age - 20) * (age - 20) + 10)
                i = Log.i("Result Available", "Value from Calculation: $camels")
                val camelString = camels.toString()
                //open next fragment & pass params
                val intent: Intent = Intent(this@Calculator, Result::class.java)
                intent.putExtra("erg", camelString)
                startActivity(intent)
            }
        }

            val maleBtn = findViewById<Button>(R.id.male)
            maleBtn.setOnClickListener {
                val img = findViewById<ImageView>(R.id.imageView11)
                val label = findViewById<TextView>(R.id.textView7)
                val slider = findViewById<SeekBar>(R.id.breastsize_input)
                label.visibility = View.INVISIBLE
                img.visibility = View.INVISIBLE
                slider.visibility = View.INVISIBLE
                slider.progress = 0
            }
            val femaleBtn = findViewById<Button>(R.id.female)
            femaleBtn.setOnClickListener {
                val img = findViewById<ImageView>(R.id.imageView11)
                val label = findViewById<TextView>(R.id.textView7)
                val slider = findViewById<SeekBar>(R.id.breastsize_input)
                label.visibility = View.VISIBLE
                img.visibility = View.VISIBLE
                slider.visibility = View.VISIBLE
            }
    }
}