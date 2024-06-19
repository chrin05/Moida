package com.example.moida.model.schedule

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moida.model.GroupDetailViewModel
import com.example.moida.model.Meeting

data class NewScheduleData(
    var scheduleName: String,
    var scheduleStartDate: String,
    var scheduleId: Int,
)

class NewScheduleViewModel(private val application: Application) : AndroidViewModel(application) {
    var newScheduleList = mutableStateListOf<NewScheduleData>()
    var indexValue = 0

    fun addSchedule(scheduleName: String, startDate: String) {
        indexValue++
        newScheduleList.add(
            NewScheduleData(scheduleName, startDate, indexValue)
        )
    }

    fun deleteSchedule(scheduleId: Int) {
        newScheduleList.removeAll { it.scheduleId == scheduleId }
    }

    fun findNameById(scheduleId: Int): String {
        return newScheduleList.find { it.scheduleId == scheduleId }?.scheduleName ?: "scheduleName : Not Found"
    }

    fun findDateById(scheduleId: Int): String {
        return newScheduleList.find { it.scheduleId == scheduleId }?.scheduleStartDate ?: "scheduleStartDate : Not Found"
    }

    fun getLastId(): Int {
        return newScheduleList[newScheduleList.size-1].scheduleId
    }
}

class NewScheduleViewModelFactory(private val meeting: Meeting) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupDetailViewModel::class.java)) {
            return GroupDetailViewModel(meeting) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}