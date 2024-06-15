package com.example.moida.model

data class Meeting(val name: String = "",
                   val imageRes: Int = 0,
                   val code: String = "",
                   val members: List<Map<String, String>> = emptyList())