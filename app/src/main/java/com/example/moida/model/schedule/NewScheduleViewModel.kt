package com.example.moida.model.schedule

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel

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
        return newScheduleList[newScheduleList.size].scheduleId
    }
}