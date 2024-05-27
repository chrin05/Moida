package com.example.moida.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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

@Composable
fun MainHome(
    todayViewModel: TodayViewModel = viewModel(),
    upcomingViewModel: UpcomingViewModel = viewModel(),
) {
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
            CustomCalendar()
        }
        item{
            TodayItemList()
        }
        items(todayViewModel.itemList) { item ->
            TodayItem(item)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            UpcomingItemList()
        }
        items(upcomingViewModel.itemList) {item ->
            UpcomingItem(item)
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}

