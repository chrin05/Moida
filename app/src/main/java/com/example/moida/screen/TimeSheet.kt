package com.example.moida.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.DateOfTimeSheet
import com.example.moida.component.TitleWithXBtn
import com.example.moida.model.Routes
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 44.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = "왼쪽버튼",
                modifier = Modifier
                    .size(16.dp)
                    .clickable {
                    }
            )
            HeadOfTime(startDate = "2024.04.04", startDay = DayOfWeek.MONDAY.value)
            Image(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = "오른쪽버튼",
                modifier = Modifier
                    .size(16.dp)
                    .clickable {
                    }
            )
        }
    }
}

@Composable
fun HeadOfTime(
    startDate: String,
    startDay: Int
) {
    val dateFormatter = DateTimeFormatter.ofPattern("MM.dd")
    var currentDate = LocalDate.parse("2024.05.05", DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    var currentDay = DayOfWeek.MONDAY

    Column(
        modifier = Modifier
    ) {
        Row(
        ) {
            for (i in 0 until 3) {
                DateOfTimeSheet(
                    date = currentDate.format(dateFormatter),
                    day = currentDay.getDisplayName(TextStyle.FULL, Locale.KOREAN)
                )

                currentDate = currentDate.plusDays(1)
                currentDay = currentDay.plus(1)
            }
        }
    }
}