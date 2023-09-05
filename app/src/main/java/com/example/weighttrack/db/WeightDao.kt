package com.example.weighttrack.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeightDao {
    @Query("SELECT * FROM weights")
    fun getAll(): List<Weight>

    @Insert
    fun save(weight: Weight)

    @Delete
    fun delete(weight: Weight)
}