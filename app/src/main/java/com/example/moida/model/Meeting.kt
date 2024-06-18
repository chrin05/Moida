package com.example.moida.model
import java.io.Serializable

data class Meeting(
    val id: String = "", //각 미팅마다 가진 고유 키
    val name: String = "",
    val imageRes: Int = 0,
    val code: String = "",
    val members: List<Map<String, String>> = emptyList()
) : Serializable