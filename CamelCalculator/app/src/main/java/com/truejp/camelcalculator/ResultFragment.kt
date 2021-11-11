package com.truejp.camelcalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.truejp.camelcalculator.database.CalculationDatabase
import com.truejp.camelcalculator.database.CalculationRepository
import com.truejp.camelcalculator.databinding.FragmentResultBinding
import com.truejp.camelcalculator.model.MainActivityViewModel
import com.truejp.camelcalculator.model.MainActivityViewModelFactory

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
        binding.shareBtn.setOnClickListener{
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey, ich habe gerade mit der coolsten App ausgerechnet, dass ich $res Kamele wert bin!!!")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Teilen via:"))
        }
        binding.textView8.text = "Du bist ".plus(ResultFragmentArgs.fromBundle(requireArguments()).result)


        //Output DB from Storage
        val database = activity?.let { CalculationDatabase.getInstance(it.applicationContext) }
        val calculationRepository = database?.let { CalculationRepository(it.calculationDao) }
        val viewModelFactory = calculationRepository?.let { MainActivityViewModelFactory(it) }
        val mainActivityViewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            )[MainActivityViewModel::class.java]
        }
        binding.viewModel = mainActivityViewModel
        binding.lifecycleOwner = this
        binding.calculationList.layoutManager = LinearLayoutManager(this.context);
        val adapter = CalculationAdapter()
        binding.calculationList.adapter = adapter
        mainActivityViewModel?.notes?.observe(viewLifecycleOwner, Observer {adapter.submitList(it)})

        return binding.root
    }
}