package com.example.moida.model.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Random

class FixedScheduleViewModel(private val repository: FixedScheduleRepo) : ViewModel() {
    private val _itemList = MutableStateFlow<List<FixedScheduleData>>(emptyList())
    var selectedItem = FixedScheduleData(-1, "", "", "", "")
    private val database = Firebase.firestore

    fun AddFixedSchedule(fixedScheduleData: FixedScheduleData) {
        viewModelScope.launch {
            repository.addFixedSchedule(fixedScheduleData)
            GetAllFixedSchedule()
        }
    }

    fun UpdateFixedSchedule(fixedScheduleData: FixedScheduleData) {
        viewModelScope.launch {
            repository.updateFixedSchedule(fixedScheduleData)
        }
    }

    fun DeleteFixedSchedule(fixedScheduleData: FixedScheduleData) {
        viewModelScope.launch {
            repository.deleteFixedSchedule(fixedScheduleData)
        }
    }

    fun GetAllFixedSchedule() {
        viewModelScope.launch {
            repository.getAllFixedSchedule().collect {
                _itemList.value = it
            }
        }
    }

    fun GetFixedSchedule(scheduleId: Int, callback: (FixedScheduleData) -> Unit) {
        viewModelScope.launch {
            repository.getFixedSchedule(scheduleId.toString()) {
                selectedItem = it
                callback(it)
            }
        }
    }
}

class FixedScheduleViewModelFactory(private val repository: FixedScheduleRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FixedScheduleViewModel::class.java)) {
            return FixedScheduleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}