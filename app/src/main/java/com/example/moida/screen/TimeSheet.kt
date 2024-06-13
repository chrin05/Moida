package com.example.moida.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.DateOfTimeSheet
import com.example.moida.component.HeadOfTime
import com.example.moida.component.NumberBlock
import com.example.moida.component.NumberSection
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
    var page by remember { mutableIntStateOf(1) }
    var startDate by remember { mutableStateOf("2024.04.04") }
    var startDay by remember { mutableStateOf(getDayofWeek(startDate)) }
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    Column {
        //제목부분
        TitleWithXBtn(
            navController = navController,
            route = Routes.CreateGroupSchedule.route,
            title = title,
            rightBtn = true
        )
        //날짜부분
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 44.dp)
        ) {
            if (page == 1) {
                Box(modifier = Modifier.size(16.dp))
            } else {
                Image(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "왼쪽버튼",
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            page -= 1
                            val date = LocalDate.parse(startDate, formatter)
                            val newDate = date.minusDays(3)
                            startDate = newDate.format(formatter)
                            startDay = getDayofWeek(startDate)
                        }
                )
            }
            HeadOfTime(startDate = startDate, startDay = startDay, page = page)
            if (page == 3) {
                Box(modifier = Modifier.size(16.dp))
            } else {
                Image(
                    painter = painterResource(id = R.drawable.chevron_right),
                    contentDescription = "오른쪽버튼",
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            page += 1
                            val date = LocalDate.parse(startDate, formatter)
                            val newDate = date.plusDays(3)
                            startDate = newDate.format(formatter)
                            startDay = getDayofWeek(startDate)
                        }
                )
            }
        }

        


        //우측 인원수 박스 : 모임인원수 넘겨줌 => 변경 필요
        NumberSection(participants = 2)
    }
}

fun getDayofWeek(startDate: String): DayOfWeek {
    // 날짜 문자열을 파싱하기 위한 포맷터 정의
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    // LocalDate 객체로 변환
    val date = LocalDate.parse(startDate, formatter)

    return date.dayOfWeek
}



