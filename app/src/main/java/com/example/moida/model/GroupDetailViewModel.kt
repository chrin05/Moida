package com.example.moida.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moida.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class GroupDetailViewModel(meeting: Meeting) : ViewModel() {
    private val _groupInfo = MutableStateFlow<GroupInfo?>(null)
    val groupInfo: StateFlow<GroupInfo?> = _groupInfo

    private val _itemList = MutableStateFlow<List<GroupItemData>>(emptyList())
    val itemList: StateFlow<List<GroupItemData>> = _itemList

    init {
        viewModelScope.launch {
            _itemList.value = listOf(
                GroupItemData("1차 스터디", "2024.05.31", "12:00"),
                GroupItemData("2차 스터디", "2024.06.07", "13:00"),
                GroupItemData("첫 회식", "2024.06.08", "18:00")
            )
            _groupInfo.value = GroupInfo(meeting.id, meeting.name, meeting.imageRes, meeting.members.size)
        }
    }
}

class GroupDetailViewModelFactory(private val meeting: Meeting) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupDetailViewModel::class.java)) {
            return GroupDetailViewModel(meeting) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
