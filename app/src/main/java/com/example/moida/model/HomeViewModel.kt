package com.example.moida.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodayViewModel() : ViewModel() {
    var itemList = mutableStateListOf<TodayItemData>()
        private set

    init {
        itemList.addAll(
            listOf(
                TodayItemData("12:00", "2차 스터디", "compose 스터디"),
                TodayItemData("18:00", "동아리 회식", "개인일정")
            )
        )
    }
    fun getItemCount(): Int {
        return itemList.size
    }
}

class UpcomingViewModel() : ViewModel() {
    var itemList = mutableStateListOf<UpcomingItemData>()
        private set

    init {
        itemList.addAll(
            listOf(
                UpcomingItemData("2024. 05. 10", "2024. 05. 17", "3차 스터디", "compose 스터디"),
                UpcomingItemData("2024. 06. 01", "2024. 06. 08", "산학협력프로젝트 회의", "산학협력프로젝트1")
            )
        )
    }
    fun getItemCount(): Int {
        return itemList.size
    }
}