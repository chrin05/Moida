package com.example.moida.model

data class GroupInfo(
    val groupName: String,
    val memberCount: Int
)

data class GroupEventData(
    val scheduleName: String,
    val date: String,
    val time: String
)
