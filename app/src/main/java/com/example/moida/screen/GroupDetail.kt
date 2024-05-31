package com.example.moida.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moida.component.GroupDetailTitle
import com.example.moida.model.GroupDetailViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun GroupDetail(
    groupDetailViewModel: GroupDetailViewModel = viewModel()
) {
    val groupInfo by groupDetailViewModel.groupInfo.collectAsState()
    val eventList by groupDetailViewModel.eventList.collectAsState()

    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    val todayEvents = eventList.groupBy { LocalDate.parse(it.date, dateFormatter) }
    val todayDate = remember { LocalDate.now() }
    var selectedEvents by remember { mutableStateOf(todayEvents[todayDate].orEmpty()) }
    var title by remember { mutableStateOf("오늘의 일정") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(color = Color.White)
    ) {
        item { 
            groupInfo?.let { GroupDetailTitle(group = it)}
        }
    }
}