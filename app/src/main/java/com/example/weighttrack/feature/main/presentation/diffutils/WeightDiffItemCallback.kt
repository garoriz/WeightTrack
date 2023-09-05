package com.example.weighttrack.feature.main.presentation.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.weighttrack.db.Weight

class WeightDiffItemCallback : DiffUtil.ItemCallback<Weight>() {
    override fun areItemsTheSame(
        oldItem: Weight,
        newItem: Weight
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Weight,
        newItem: Weight
    ): Boolean = oldItem == newItem
}