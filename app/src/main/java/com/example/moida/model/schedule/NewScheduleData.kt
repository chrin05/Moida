package com.example.moida.model.schedule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShareViewModel : ViewModel() {
//    var scheduleName by mutableStateOf("")
//    var scheduleDate by mutableStateOf("")
//    var scheduleTime by mutableStateOf("")

    private val _scheduleName = MutableStateFlow("")
    private val _scheduleDate = MutableStateFlow("")
    private val _scheduleTime = MutableStateFlow("")
    val scheduleName: StateFlow<String> get() = _scheduleName
    val scheduleDate: StateFlow<String> get() = _scheduleDate
    val scheduleTime: StateFlow<String> get() = _scheduleTime

    fun changeSName(name: String) {
        _scheduleName.value = name
    }

    fun changeSDate(date: String) {
        viewModelScope.launch {
            _scheduleDate.value = date
        }
    }

    fun changeSTime(time: String) {
        _scheduleTime.value = time
    }
}