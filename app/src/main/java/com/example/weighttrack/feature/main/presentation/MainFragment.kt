package com.example.weighttrack.feature.main.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.weighttrack.R
import com.example.weighttrack.databinding.FragmentMainBinding
import com.example.weighttrack.db.AppDatabase
import com.example.weighttrack.db.Weight
import com.example.weighttrack.feature.main.presentation.adapter.WeightListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private var weightListAdapter: WeightListAdapter? = null
    private lateinit var db: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        db = AppDatabase(requireContext())

        with(binding) {
            weightListAdapter = WeightListAdapter { clickedWeight ->
                deleteWeight(clickedWeight)
            }

            binding.weights.run {
                adapter = weightListAdapter
            }

            submitWeights()

            fabAdd.setOnClickListener {
                view.findNavController().navigate(
                    R.id.action_mainFragment_to_addingWeightFragment
                )
            }
        }
    }

    private fun submitWeights() {
        CoroutineScope(Dispatchers.IO).launch {
            val weights = db.weightDao().getAll() as MutableList<Weight>
            weights.sortByDescending { it.date }
            weightListAdapter?.submitList(weights)
        }
    }

    private fun deleteWeight(clickedWeight: Weight) {
        CoroutineScope(Dispatchers.IO).launch {
            db.weightDao().delete(clickedWeight)
            submitWeights()
        }
    }
}