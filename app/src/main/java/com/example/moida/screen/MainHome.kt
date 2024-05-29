package com.example.moida.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moida.component.CustomCalendar
import com.example.moida.component.HomeTitle
import com.example.moida.component.TodayItem
import com.example.moida.component.TodayItemList
import com.example.moida.component.UpcomingItem
import com.example.moida.component.UpcomingItemList
import com.example.moida.model.TodayViewModel
import com.example.moida.model.UpcomingViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MainHome(
    todayViewModel: TodayViewModel = viewModel(),
    upcomingViewModel: UpcomingViewModel = viewModel(),
) {
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    val todayEvents = todayViewModel.itemList.groupBy { LocalDate.parse(it.date, dateFormatter) }
    val todayDate = remember { LocalDate.now() }
    var selectedEvents by remember { mutableStateOf(todayEvents[todayDate].orEmpty()) }
    var title by remember { mutableStateOf("오늘의 일정") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(color = Color.White)
    ) {

        item{
            HomeTitle()
        }
        item{
            CustomCalendar(
                events = todayEvents,
                onDateClick = {date ->
                    selectedEvents = todayEvents[date].orEmpty()
                },
                updateTitle = {it ->
                    title = it
                }
            )
        }
        item{
            TodayItemList(selectedEvents.size, title)
        }
        // 오늘의 일정 리스트
        items(selectedEvents) { item ->
            TodayItem(item)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            UpcomingItemList()
        }
        // 대기 중인 일정 리스트
        items(upcomingViewModel.itemList) {item ->
            UpcomingItem(item)
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}

