package com.truejp.camelcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_calculator,container,false)
        binding.calculate.setOnClickListener{view: View -> if (checkCalculator()) view.findNavController().navigate(R.id.resultFragment)}
        return binding.root
    }
    private fun checkCalculator(): Boolean {
        //check inputs and calc res
        this.result = 1
        return true
    }
}