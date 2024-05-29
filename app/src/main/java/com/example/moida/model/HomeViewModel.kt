package com.example.moida.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.format.DateTimeFormatter

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
class TodayViewModel() : ViewModel() {
    var itemList = mutableStateListOf<TodayItemData>()
        private set

    init {
        itemList.addAll(
            listOf(
                TodayItemData("2024.05.08", "11:00", "채린이랑 엽떡", "개인 일정"),
                TodayItemData("2024.05.08", "19:00", "어버이날 가족 외식", "개인 일정"),
                TodayItemData("2024.05.14", "18:30", "롯데 vs 키움 야구장", "개인 일정"),
                TodayItemData("2024.05.18", "8:30", "서울신문하프마라톤", "RIKU"),
                TodayItemData("2024.05.22", "7:00", "스쿼시 수업", "스쿼시 클럽"),
                TodayItemData("2024.05.27", "23:00", "모프 회의", "모프 모이다"),
                TodayItemData("2024.05.29", "7:00", "스쿼시 수업", "스쿼시 클럽"),
                TodayItemData("2024.05.29","12:00", "2차 스터디", "compose 스터디"),
                TodayItemData("2024.06.01","18:00", "동아리 회식", "개인일정")
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
                UpcomingItemData("2024.05.10", "2024. 05. 17", "3차 스터디", "compose 스터디"),
                UpcomingItemData("2024.06.01", "2024. 06. 08", "산학협력프로젝트 회의", "산학협력프로젝트1")
            )
        )
    }
    fun getItemCount(): Int {
        return itemList.size
    }
}