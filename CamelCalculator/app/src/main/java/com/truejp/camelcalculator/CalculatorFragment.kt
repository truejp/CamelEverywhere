package com.truejp.camelcalculator
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.truejp.camelcalculator.database.CalculationDatabase
import com.truejp.camelcalculator.database.CalculationRepository
import com.truejp.camelcalculator.databinding.FragmentCalculatorBinding
import com.truejp.camelcalculator.model.MainActivityViewModel
import com.truejp.camelcalculator.model.MainActivityViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkCalculator(): Boolean {
        val breast = binding.breastsizeInput.progress
        val eye = binding.eyecolorInput.progress
        val hair = binding.haircolorInput.progress
        val body = binding.bodyInput.progress
        val name = binding.nameInput.text
        val ageHelper = binding.ageInput.text
        val comment = binding.comment.text
        val weightHelper = binding.weigthInput.text
        var eyeText = ""
        var genderText = ""
        var bodyText = ""
        var hairText = ""
        var breastText = ""
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formatted = current.format(formatter)
        if (ageHelper.trim().isEmpty() || weightHelper.trim().isEmpty() || name.trim().isEmpty()) {
            Toast.makeText(activity,"Bitte prüfe deine Eingaben!",Toast.LENGTH_SHORT).show();
            return false
        } else {
            var age: Int = Integer.parseInt(ageHelper.toString())
            if (age < 0) age = 0
            if (age > 63) age = 63
            var weight: Int = Integer.parseInt(weightHelper.toString())
            if (weight < 20) weight = 20
            if (weight > 110) weight = 110
            var i = Log.i("No Error", "No missing values")
            var gender: Int = 10
            when (binding.genderInput.checkedRadioButtonId) {
                R.id.male -> {
                    print("Correct answer :p")
                    genderText = "Mann"
                }
                R.id.female -> {
                    print("female")
                    gender = 5
                    genderText = "Frau"
                }
            }
            ((body + eye + breast + hair) * 2 + (-1 / 200 * (weight - 65) * (weight - 65) + 10) + gender + (-1 / 200 * (age - 20) * (age - 20) + 10)).also { this.result = it }
            val database = activity?.let { CalculationDatabase.getInstance(it.applicationContext) }
            val calculationRepository = database?.let { CalculationRepository(it.calculationDao) }
            val viewModelFactory = calculationRepository?.let { MainActivityViewModelFactory(it) }
            val mainActivityViewModel = viewModelFactory?.let {
                ViewModelProvider(this,
                    it
                )[MainActivityViewModel::class.java]
            }
            when (eye) {
                0 -> eyeText = "Braun"
                1 -> eyeText = "Grün"
                2 -> eyeText = "Grau"
                3 -> eyeText = "Blau"
            }
            when (hair) {
                0 -> hairText = "Schwarz"
                1 -> hairText = "Grau"
                2 -> hairText = "Braun"
                3 -> hairText = "Rot"
                4 -> hairText = "Blond"
            }
            when (body) {
                0 -> bodyText = "mager"
                1 -> bodyText = "dick"
                2 -> bodyText = "dünn"
                3 -> bodyText = "normal"
                4 -> bodyText = "trainiert"
            }
            when (breast) {
                0 -> breastText = "keine"
                1 -> breastText = "okay"
                2 -> breastText = "stabil"
                3 -> breastText = "zu wild"
            }

            mainActivityViewModel?.userText = name.toString()
            mainActivityViewModel?.gender = genderText
            mainActivityViewModel?.age = age.toString()
            mainActivityViewModel?.weigth = weight.toString()
            mainActivityViewModel?.body = bodyText
            mainActivityViewModel?.eyecolor = eyeText
            mainActivityViewModel?.haircolor = hairText
            mainActivityViewModel?.breastsize = breastText
            mainActivityViewModel?.camels = this.result.toString()
            mainActivityViewModel?.comment = comment.toString()
            mainActivityViewModel?.date = formatted
            mainActivityViewModel?.insert()
            return true
        }
    }
}