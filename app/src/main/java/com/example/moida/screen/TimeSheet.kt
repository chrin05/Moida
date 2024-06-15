package com.example.moida.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.BottomBtn
import com.example.moida.component.HeadOfTime
import com.example.moida.component.NumberSection
import com.example.moida.component.ShowTimeLine
import com.example.moida.component.TimeBlock
import com.example.moida.component.TimeBlockGroup
import com.example.moida.component.TitleWithXBtn
import com.example.moida.model.Routes
import com.example.moida.model.schedule.NewScheduleViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TimeSheet(
    navController: NavHostController,
    newScheduleViewModel: NewScheduleViewModel = viewModel(),
    scheduleId: Int
) {
    var page by remember { mutableIntStateOf(1) }
    var startDate by remember { mutableStateOf("2024.04.04") }
    var startDay by remember { mutableStateOf(getDayofWeek(startDate)) }
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    val timeList = intArrayOf(0,0,1,1,2,2,3,3,4,4,5,5,0,0,1,1,2,2,3,3,4,4,5,5)
    val memberCount = 5

    Column {
        //제목부분
        TitleWithXBtn(
            navController = navController,
            route = Routes.CreateGroupSchedule.route,
            title = newScheduleViewModel.findNameById(scheduleId),
            rightBtn = false
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
                        .padding(end = 4.dp)
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
                        .padding(start = 4.dp)
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

        //시간테이블 부분
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //시간대
            ShowTimeLine()
            //시간화면보여줌
            TimeBlockGroup(page = page, memberCount = memberCount, startDate = startDate)
            //우측 인원수 박스 : 모임인원수 넘겨줌 => 변경 필요
            NumberSection(memberCount = 2)
        }

        Spacer(modifier = Modifier.weight(1f))

        //버튼 입력
        Box(modifier = Modifier
            .padding(horizontal = 24.dp)) {
            BottomBtn(
                navController = navController,
                route = Routes.TimeInput.route,
                value = "",
                btnName = "내 시간 입력하기",
                activate = true
            )
        }
    }
}

fun getDayofWeek(startDate: String): DayOfWeek {
    // 날짜 문자열을 파싱하기 위한 포맷터 정의
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    // LocalDate 객체로 변환
    val date = LocalDate.parse(startDate, formatter)

    return date.dayOfWeek
}



