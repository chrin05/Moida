package com.example.moida.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.DateField
import com.example.moida.component.NameTextField
import com.example.moida.component.TimeField
import com.example.moida.component.Title
import com.example.moida.model.BottomNavItem
import com.example.moida.model.schedule.ShareViewModel
import java.time.format.DateTimeFormatter

@Composable
fun CreateMySchedule(
    navController: NavHostController,
    shareViewModel: ShareViewModel,
) {
    val scheduleName by shareViewModel.scheduleName.collectAsState()
    val scheduleDate by shareViewModel.scheduleDate.collectAsState()
    val scheduleTime by shareViewModel.scheduleTime.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Title(
            navController = navController,
            route = BottomNavItem.Home.route,
            title = "개인 일정 추가",
            rightBtn = "추가",
            rightColor = R.color.main_blue
        )

        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 40.dp, end = 24.dp)
        ) {
            NameTextField(title = "일정 이름", name = scheduleName, onValueChange = {
                shareViewModel.changeSName(it)
                }, "일정 이름 입력")
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            DateField(navController, title = "일정 날짜", date = scheduleDate, onValueChange = {
                shareViewModel.changeSDate(it)})
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            TimeField(navController, title = "일정 시간", time = scheduleTime, onValueChange = {
                shareViewModel.changeSTime(it)})
        }
    }
}
