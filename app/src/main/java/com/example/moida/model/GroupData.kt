package com.example.moida.model

data class GroupInfo(
    val groupName: String,
    val groupImg: Int,
    val memberCount: Int
)

data class GroupItemData(
    val scheduleName: String,
    val date: String,
    val time: String
)
