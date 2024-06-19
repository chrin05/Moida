package com.example.moida.model

data class GroupInfo(
    val groupId: String, //각 미팅마다 가진 고유 키
    val groupName: String,
    val groupImg: Int,
    val memberCount: Int
)

data class GroupItemData(
    val scheduleName: String,
    val date: String,
    val time: String
)
