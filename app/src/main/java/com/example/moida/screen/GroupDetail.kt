package com.example.moida.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.moida.component.GroupItem
import com.example.moida.component.MainCalendar
import com.example.moida.component.TodayItemList
import com.example.moida.model.GroupDetailViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun GroupDetail(
    groupDetailViewModel: GroupDetailViewModel = viewModel()
) {
    val groupInfo by groupDetailViewModel.groupInfo.collectAsState()
    val itemList by groupDetailViewModel.itemList.collectAsState()

    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    val todayEvents = itemList.groupBy { LocalDate.parse(it.date, dateFormatter) }
    val todayDate = remember { LocalDate.now() }
    var selectedEvents by remember { mutableStateOf(todayEvents[todayDate].orEmpty()) }
    var title by remember { mutableStateOf("오늘의 일정") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        item { 
            groupInfo?.let { GroupDetailTitle(group = it)}
        }
        item{
            MainCalendar(
                events = todayEvents,
                onDateClick = {date ->
                    selectedEvents = todayEvents[date].orEmpty()
                },
                updateTitle = {it ->
                    title = it
                },
                hasEvents = {date, events ->
                    events[date]?.isNotEmpty() == true
                }
            )
        }
        item{
            TodayItemList(selectedEvents.size, title)
        }
        // 오늘의 일정 리스트
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 100.dp)
            ) {
                selectedEvents.forEach { item ->
                    GroupItem(item)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}