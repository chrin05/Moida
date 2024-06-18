package com.example.moida.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.BottomBtn
import com.example.moida.component.HeadOfTime
import com.example.moida.component.ShowTimeLine
import com.example.moida.component.TimeBlockInputGroup
import com.example.moida.component.TitleWithXBtn
import com.example.moida.model.Routes
import com.example.moida.ui.theme.Pretendard
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TimeInput(
    navController: NavHostController,
) {
    var page by remember { mutableIntStateOf(1) }
    var startDate by remember { mutableStateOf("2024.04.04") }
    var startDay by remember { mutableStateOf(getDayofWeek(startDate)) }
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    //val timeList by remember { mutableStateListOf<Int>(0) }
    //val memberCount = 5
    var isBtnClicked by remember {
        mutableStateOf(false)
    }

    Column {
        //제목부분
        TitleWithXBtn(
            navController = navController,
            route = Routes.TimeSheet.route,
            title = "내 시간 입력하기",
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
            TimeBlockInputGroup(page = page, startDate = startDate)
            //가능한시간 없음
            Button(
                onClick = {
                    isBtnClicked = !isBtnClicked
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black // 텍스트 색상 또는 아이콘 색상 설정
                ),
                elevation = null, // 그림자 제거
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    var painter = if (!isBtnClicked) R.drawable.ic_timeinput else R.drawable.ic_timeinput_clicked
                    var color = if (!isBtnClicked) R.color.disabled else R.color.main_blue

                    Image(
                        painter = painterResource(id = painter),
                        contentDescription = "가능한시간없음",
                    )
                    Text(
                        text = "가능한 시간 없음",
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontFamily = Pretendard,
                        fontWeight = FontWeight(400),
                        color = colorResource(id = color),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        var activate = true
        //버튼 입력
        Box(modifier = Modifier
            .padding(horizontal = 24.dp)) {
            BottomBtn(
                navController = navController,
                route = Routes.TimeSheet.route,
                value = "",
                btnName = "저장",
                activate = activate
            )
        }
    }
}


