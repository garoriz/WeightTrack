package com.example.weighttrack.feature.main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weighttrack.databinding.ItemWeightBinding
import com.example.weighttrack.db.Weight

class WeightHolder(
    private val binding: ItemWeightBinding,
    private val action: (Weight) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var weight: Weight? = null

    fun bind(item: Weight) {
        this.weight = item
        with(binding) {
            tvWeight.text = item.weight.toString()
            tvDate.text = item.date
            ibDelete.setOnClickListener {
                weight?.also(action)
            }
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            action: (Weight) -> Unit
        ) = WeightHolder(
            ItemWeightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}