package com.example.moida.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.DateField
import com.example.moida.component.NameTextField
import com.example.moida.component.TimeField
import com.example.moida.component.Title
import com.example.moida.model.BottomNavItem

@Composable
fun ScheduleEdit(
    navController: NavHostController,
) {
    val scheduleName by remember {
        mutableStateOf("")
    }
    val scheduleDate by remember {
        mutableStateOf("")
    }
    val scheduleTime by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Title(
            navController = navController,
            route = BottomNavItem.Home.route,
            title = "상세 페이지",
            rightBtn = "완료",
            rightColor = R.color.main_blue
        )

        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 40.dp, end = 24.dp)
        ) {
            NameTextField(title = "일정 이름", name = scheduleName, onValueChange = {
             }, "일정 이름 입력")
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            DateField(navController, title = "일정 날짜", date = scheduleDate, onValueChange = {

            })
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            TimeField(navController, title = "일정 시간", time = scheduleTime, onValueChange = {

            })
        }
    }
}