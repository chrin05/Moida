package com.example.moida.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.moida.R
import com.example.moida.component.Title

@Composable
fun CreateMySchedule() {
    Column {
        Title(title = "개인 일정 추가", rightBtn = "추가", rightColor = R.color.main_blue)
        Title(title = "개인 일정 추가", rightBtn = "    ", rightColor = null)
    }
}