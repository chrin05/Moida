package com.example.moida.model

data class TodayItemData(
    val time: String,
    val name: String,
    val group: String
)

data class UpcomingItemData(
    val startDate: String,
    val endDate: String,
    val name: String,
    val group: String
)