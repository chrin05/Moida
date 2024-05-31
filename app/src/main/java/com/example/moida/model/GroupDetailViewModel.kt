package com.example.moida.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moida.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroupDetailViewModel() : ViewModel() {
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
            _groupInfo.value = GroupInfo("Compose 스터디", R.drawable.sample_image8, 4)
        }
    }
}