package com.D121201075.mytask.repository

import androidx.lifecycle.LiveData
import com.D121201075.mytask.data.TugasDao
import com.D121201075.mytask.model.Tugas

class TugasRepository (private val tugasDao: TugasDao){
    val readAllData:LiveData<List<Tugas>> = tugasDao.readAllData()
    val readAllDataHistory:LiveData<List<Tugas>> = tugasDao.readAllDataHistory()
    suspend fun addTugas(tugas: Tugas){
        tugasDao.addTugas(tugas)
    }
    suspend fun updateTugas(tugas: Tugas){
        tugasDao.updateTugas(tugas)
    }
    suspend fun deleteTugas(tugas: Tugas){
        tugasDao.deleteTugas(tugas)
    }
    suspend fun deleteAllHistory(){
        tugasDao.deleteAllHistory()
    }
}