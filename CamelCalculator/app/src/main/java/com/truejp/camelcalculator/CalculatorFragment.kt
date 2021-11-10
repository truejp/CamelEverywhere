package com.truejp.camelcalculator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.truejp.camelcalculator.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    var result = 0
    private lateinit var binding: FragmentCalculatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false)
        binding.calculate.setOnClickListener { view: View ->
            if (checkCalculator()) view.findNavController().navigate(R.id.resultFragment)
        }
        binding.male.setOnClickListener { view: View ->
            binding.imageView11.visibility = View.INVISIBLE
            binding.textView7.visibility = View.INVISIBLE
            binding.breastsizeInput.visibility = View.INVISIBLE
            binding.breastsizeInput.progress = 0
        }
        binding.female.setOnClickListener { view: View ->
            binding.imageView11.visibility = View.VISIBLE
            binding.textView7.visibility = View.VISIBLE
            binding.breastsizeInput.visibility = View.VISIBLE
        }
        return binding.root
    }

    private fun checkCalculator(): Boolean {
        //check inputs and calc res
        val breast = binding.breastsizeInput.progress
        val eye = binding.eyecolorInput.progress
        val hair = binding.haircolorInput.progress
        val body = binding.bodyInput.progress
        val name = binding.nameInput.text
        val ageHelper = binding.ageInput.text
        val weightHelper = binding.weigthInput.text
        if (ageHelper.trim().isEmpty() || weightHelper.trim().isEmpty() || name.trim().isEmpty()) {
            //Some User input missing
            Toast.makeText(activity,"Bitte prüfe deine Eingaben!",Toast.LENGTH_SHORT).show();
            return false
        } else {
            //User Input complete
            val age: Int = Integer.parseInt(ageHelper.toString())
            val weight: Int = Integer.parseInt(weightHelper.toString())
            var i = Log.i("No Error", "No missing values")
            //Calculate Gender Bonus
            var gender = 10
            when (binding.genderInput.checkedRadioButtonId) {
                R.id.male -> {
                    print("Correct answer :p")
                }
                R.id.female -> {
                    print("female")
                    var gender = 5
                }
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
            var camels = (body + eye + breast + hair) * 2 + (1 / 200 * (weight - 65) * (weight - 65) + 10) + gender + (1 / 200 * (age - 20) * (age - 20) + 10)
            i = Log.i("Result Available", "Value from Calculation: $camels")
            val camelString = camels.toString()
            this.result = 1
            return true
        }
    }
}