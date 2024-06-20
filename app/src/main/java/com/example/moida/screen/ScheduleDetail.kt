package com.example.moida.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.ParticipantsField
import com.example.moida.component.TextField
import com.example.moida.component.Title
import com.example.moida.model.BottomNavItem
import com.example.moida.model.schedule.FixedScheduleData
import com.example.moida.model.schedule.FixedScheduleViewModel
import com.example.moida.model.schedule.ScheduleData

@Composable
fun ScheduleDetail(
    navController: NavHostController,
    fixedScheduleViewModel: FixedScheduleViewModel,
    scheduleId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var name by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }
        var time by remember { mutableStateOf("") }

        var selectedItem by remember {
            mutableStateOf<FixedScheduleData?>(null)
        }

        LaunchedEffect(selectedItem) {
            initScheduleInfo(scheduleId, fixedScheduleViewModel) {
                name = it.scheduleName
                date = it.scheduleDate
                time = it.scheduleTime
            }
        }

        Title(
            navController = navController,
            route = BottomNavItem.Home.route,
            title = "상세 페이지",
            rightBtn = "햄버거",
            rightColor = R.color.main_blue
        )

        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 40.dp, end = 24.dp)
        ) {
            TextField(title = "일정 이름", value = name)
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            TextField(title = "일정 날짜", value = date)
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            TextField(title = "일정 시간", value = time)
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
//            ParticipantsField()
        }
    }
}

fun initScheduleInfo(
    scheduleId: Int,
    fixedScheduleViewModel: FixedScheduleViewModel,
    function: (FixedScheduleData) -> Unit
) {
    fixedScheduleViewModel.GetFixedSchedule(scheduleId) {
        function(it)
    }
}
