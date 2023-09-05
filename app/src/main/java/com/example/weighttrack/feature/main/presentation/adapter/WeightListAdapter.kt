package com.example.weighttrack.feature.main.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weighttrack.db.Weight
import com.example.weighttrack.feature.main.presentation.diffutils.WeightDiffItemCallback

class WeightListAdapter(
    private val action: (Weight) -> Unit,
) : ListAdapter<Weight, WeightHolder>(WeightDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeightHolder = WeightHolder.create(parent, action)

    override fun onBindViewHolder(holder: WeightHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Weight>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}