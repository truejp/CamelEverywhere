package com.truejp.camelcalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.truejp.camelcalculator.database.Calculation
import com.truejp.camelcalculator.databinding.CalculationItemViewBinding

class CalculationAdapter: ListAdapter<Calculation, CalculationAdapter.ViewHolder>(NoteDiffCallback()) {
    class ViewHolder private constructor(val binding: CalculationItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Calculation) {
            binding.calculationEntity = item
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CalculationItemViewBinding.inflate(layoutInflater, parent,false)
                return ViewHolder(binding)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
class NoteDiffCallback : DiffUtil.ItemCallback<Calculation>(){
    override fun areItemsTheSame(oldItem: Calculation, newItem: Calculation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Calculation, newItem: Calculation): Boolean {
        return oldItem == newItem
    }

}