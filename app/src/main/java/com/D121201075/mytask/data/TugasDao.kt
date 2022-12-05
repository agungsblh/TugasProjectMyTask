package com.D121201075.mytask.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.D121201075.mytask.model.Tugas

@Dao
interface TugasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTugas(tugas: Tugas)

    @Query("SELECT * FROM tugas WHERE status='Belum Selesai' ORDER BY id ASC")
    fun readAllData():LiveData<List<Tugas>>

    @Query("SELECT * FROM tugas WHERE status='Selesai'")
    fun readAllDataHistory():LiveData<List<Tugas>>
    @Update
    suspend fun updateTugas(tugas: Tugas)

    @Delete
    suspend fun deleteTugas(tugas: Tugas)

    @Query("DELETE FROM tugas WHERE status='Selesai'")
    suspend fun deleteAllHistory()
}