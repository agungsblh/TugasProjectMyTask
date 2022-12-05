package com.D121201075.mytask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.D121201075.mytask.model.Tugas
import com.D121201075.mytask.data.TugasDatabase
import com.D121201075.mytask.repository.TugasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TugasViewModel (application: Application):AndroidViewModel(application){
    val readAllData:LiveData<List<Tugas>>
    val readAllDataHistory:LiveData<List<Tugas>>

    private val repository: TugasRepository
    init {
        val tugasDao = TugasDatabase.getDatabase(application).tugasDao()
        repository = TugasRepository(tugasDao)
        readAllData = repository.readAllData
        readAllDataHistory = repository.readAllDataHistory
    }

    fun addTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTugas(tugas)
        }
    }

    fun updateTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTugas(tugas)
        }
    }

    fun deleteTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTugas(tugas)
        }
    }
    fun deleteAllHistory(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllHistory()
        }
    }

}