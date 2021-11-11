package com.truejp.camelcalculator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.truejp.camelcalculator.database.CalculationDatabase
import com.truejp.camelcalculator.database.CalculationRepository
import com.truejp.camelcalculator.databinding.FragmentCalculatorBinding
import com.truejp.camelcalculator.model.MainActivityViewModel
import com.truejp.camelcalculator.model.MainActivityViewModelFactory

class CalculatorFragment : Fragment() {
    var result = 0
    private lateinit var binding: FragmentCalculatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false)
        binding.calculate.setOnClickListener { view: View ->
            if (checkCalculator()) view.findNavController().navigate(CalculatorFragmentDirections.toResultFragment(this.result.toString()))
        }
        binding.male.setOnClickListener {
            binding.imageView11.visibility = View.INVISIBLE
            binding.textView7.visibility = View.INVISIBLE
            binding.breastsizeInput.visibility = View.INVISIBLE
            binding.breastsizeInput.progress = 0
        }
        binding.female.setOnClickListener {
            binding.imageView11.visibility = View.VISIBLE
            binding.textView7.visibility = View.VISIBLE
            binding.breastsizeInput.visibility = View.VISIBLE
        }
        return binding.root
    }

    private fun checkCalculator(): Boolean {
        val breast = binding.breastsizeInput.progress
        val eye = binding.eyecolorInput.progress
        val hair = binding.haircolorInput.progress
        val body = binding.bodyInput.progress
        val name = binding.nameInput.text
        val ageHelper = binding.ageInput.text
        val weightHelper = binding.weigthInput.text
        if (ageHelper.trim().isEmpty() || weightHelper.trim().isEmpty() || name.trim().isEmpty()) {
            Toast.makeText(activity,"Bitte prüfe deine Eingaben!",Toast.LENGTH_SHORT).show();
            return false
        } else {
            val age: Int = Integer.parseInt(ageHelper.toString())
            val weight: Int = Integer.parseInt(weightHelper.toString())
            var i = Log.i("No Error", "No missing values")
            var gender: Int = 10
            when (binding.genderInput.checkedRadioButtonId) {
                R.id.male -> {
                    print("Correct answer :p")
                }
                R.id.female -> {
                    print("female")
                    gender = 5
                }
            }
            ((body + eye + breast + hair) * 2 + (1 / 200 * (weight - 65) * (weight - 65) + 10) + gender + (1 / 200 * (age - 20) * (age - 20) + 10)).also { this.result = it }

            //Save User Input to DB
            val database = activity?.let { CalculationDatabase.getInstance(it.applicationContext) }
            val calculationRepository = database?.let { CalculationRepository(it.calculationDao) }
            val viewModelFactory = calculationRepository?.let { MainActivityViewModelFactory(it) }
            val mainActivityViewModel = viewModelFactory?.let {
                ViewModelProvider(this,
                    it
                )[MainActivityViewModel::class.java]
            }
            mainActivityViewModel?.userText = name.toString()
            mainActivityViewModel?.gender = false
            mainActivityViewModel?.age = age
            mainActivityViewModel?.weigth = weight
            mainActivityViewModel?.body = body
            mainActivityViewModel?.eyecolor = eye
            mainActivityViewModel?.haircolor = hair
            mainActivityViewModel?.breastsize = breast
            mainActivityViewModel?.camels = this.result
            mainActivityViewModel?.insert()
            return true
        }
    }
}