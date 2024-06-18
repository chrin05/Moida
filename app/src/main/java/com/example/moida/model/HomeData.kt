package com.example.moida.model

data class TodayItemData(
    val date: String = "",
    val time: String = "",
    val name: String = "",
    val category: String = ""
)

data class UpcomingItemData(
    val startDate: String = "",
    val endDate: String = "",
    val name: String = "",
    val category: String = ""
)