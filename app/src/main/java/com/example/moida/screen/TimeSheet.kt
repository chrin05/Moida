package com.example.moida.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.moida.component.HeadOfTime
import com.example.moida.component.TitleWithXBtn
import com.example.moida.model.Routes
import java.time.DayOfWeek

@Composable
fun TimeSheet(
    navController: NavHostController,
    title: String
) {
    Column {
        TitleWithXBtn(
            navController = navController,
            route = Routes.CreateGroupSchedule.route,
            title = title,
            rightBtn = true
        )

        HeadOfTime(startDate = "2024.04.04", startDay = DayOfWeek.MONDAY.value)
    }



}