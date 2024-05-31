package com.example.moida.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroupDetailViewModel() : ViewModel() {
    private val _groupInfo = MutableStateFlow<GroupInfo?>(null)
    val groupInfo: StateFlow<GroupInfo?> = _groupInfo

    private val _eventList = MutableStateFlow<List<GroupEventData>>(emptyList())
    val eventList: StateFlow<List<GroupEventData>> = _eventList
    init {
        viewModelScope.launch {
            _eventList.value = listOf(
                GroupEventData("1차 스터디", "2024.05.31", "12:00"),
                GroupEventData("2차 스터디", "2024.06.07", "13:00"),
                GroupEventData("첫 회식", "2024.06.08", "18:00")
            )
            _groupInfo.value = GroupInfo("Compose 스터디", 4)
        }
    }
}