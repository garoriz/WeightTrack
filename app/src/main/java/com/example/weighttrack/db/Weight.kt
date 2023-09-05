package com.example.weighttrack.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "weights")
data class Weight(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val weight: Double,
    val date: String,
) {
    @Ignore
    constructor(weight: Double, date: String) :
            this(0, weight, date)
}
