package com.example.weighttrack.feature.addingweight.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.weighttrack.R
import com.example.weighttrack.databinding.FragmentAddingWeightBinding
import com.example.weighttrack.db.AppDatabase
import com.example.weighttrack.db.Weight
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId


class AddingWeightFragment : Fragment(R.layout.fragment_adding_weight) {
    private lateinit var binding: FragmentAddingWeightBinding
    private lateinit var db: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(requireContext())

        binding = FragmentAddingWeightBinding.bind(view)

        with(binding) {
            setDate()

            btnDate.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()
                datePicker.show(childFragmentManager, "tag")

                datePicker.addOnPositiveButtonClickListener {
                    val localDate = localDateFromTimestamp(it)
                    btnDate.text = localDate.toString()
                }
            }

            floatingActionButton.setOnClickListener {
                CoroutineScope(IO).launch {
                    var kg = tilKg.editText?.text.toString()
                    if (kg == "")
                        kg = "0"
                    db.weightDao().save(
                        Weight(
                            kg.toDouble(),
                            btnDate.text.toString()
                        )
                    )
                }

                view.findNavController().popBackStack()
            }
        }
    }

    private fun setDate() {
        with(binding) {
            btnDate.text = localDateFromTimestamp(System.currentTimeMillis()).toString()
        }
    }

    private fun localDateFromTimestamp(timestamp: Long): LocalDate = Instant.ofEpochMilli(timestamp)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}