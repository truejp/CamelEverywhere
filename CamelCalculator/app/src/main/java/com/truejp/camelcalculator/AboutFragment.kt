package com.truejp.camelcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.truejp.camelcalculator.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about,container,false)
        val res = ResultFragmentArgs.fromBundle(requireArguments()).result
        binding.infoCloseBtn.setOnClickListener{view: View -> view.findNavController().navigate(AboutFragmentDirections.toResultFragment(res))}
        return binding.root
    }
}