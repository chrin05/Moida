package com.example.moida.model.schedule

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moida.model.GroupDetailViewModel
import com.example.moida.model.Meeting
import com.example.moida.model.TodayItemData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _itemList = MutableStateFlow<List<ScheduleData>>(emptyList())
    val itemList: StateFlow<List<ScheduleData>> = _itemList
    private val database = Firebase.firestore

    init {
        viewModelScope.launch {
            database.collection("scheduleData")
                .get()
                .addOnSuccessListener {docs ->
                    if(!docs.isEmpty) {
                        val items = docs.map {doc ->
                            val item = doc.toObject(ScheduleData::class.java)
                            item
                        }
                        _itemList.value = items
                    } else {
                        Log.d("ScheduleViewModel", "No items found in scheduleDate collection")
                    }
                }
                .addOnFailureListener {
                    Log.e("ScheduleViewModel", "Error getting scheduleDate", it)
                }
        }
    }
}

class ScheduleViewModelFactory(private val meeting: Meeting) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupDetailViewModel::class.java)) {
            return GroupDetailViewModel(meeting) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}