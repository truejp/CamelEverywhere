package com.truejp.camelcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.truejp.camelcalculator.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_result,container,false)
        val res: String = ResultFragmentArgs.fromBundle(requireArguments()).result
        binding.showInfo.setOnClickListener{view: View -> view.findNavController().navigate(ResultFragmentDirections.toAboutFragment(res))}
        binding.resCloseBtn.setOnClickListener{view: View -> view.findNavController().navigate(R.id.calculatorFragment)}
        binding.textView8.text = "Du bist ".plus(ResultFragmentArgs.fromBundle(requireArguments()).result)
        return binding.root
    }
}