package com.example.moida.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moida.R
import com.example.moida.ui.theme.Pretendard
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HeadOfTime( //날짜+요일 3개 출력
    startDate: String,
    startDay: DayOfWeek,
    page: Int
) {
    val dateFormatter = DateTimeFormatter.ofPattern("MM.dd")
    var currentDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    var currentDay = startDay

    Column(
        modifier = Modifier
    ) {
        Row(
        ) {
            if (page==3) {
                DateOfTimeSheet(
                    date = currentDate.format(dateFormatter),
                    day = currentDay.getDisplayName(java.time.format.TextStyle.FULL, Locale.KOREAN)
                )
            } else {
                for (i in 0 until 3) {
                    DateOfTimeSheet(
                        date = currentDate.format(dateFormatter),
                        day = currentDay.getDisplayName(java.time.format.TextStyle.FULL, Locale.KOREAN)
                    )

                    currentDate = currentDate.plusDays(1)
                    currentDay = currentDay.plus(1)
                }
            }
        }
    }
}

@Composable
fun DateOfTimeSheet( //날짜+요일
    date: String,
    day: String
) {
    val textStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 2.52.sp,
        fontFamily = Pretendard,
        fontWeight = FontWeight(700),
        color = colorResource(id = R.color.gray_800),
        textAlign = TextAlign.Center,
    )

    Row(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        val dayKorean = day.substring(0, 1)
        Text(text = date, style = textStyle)
        Spacer(modifier = Modifier.padding(end = 2.dp))
        Text(text = dayKorean, style = textStyle)
    }
}

@Composable
fun NumberSection(participants: Int) { //숫자블록전체
    if (participants < 4) {
        var end = 2 //기본으로 0,1,2
        if (participants == 3) end = 3 // 3명인 경우 0,1,2,3
        for (i in 0..end) {
            NumberBlock(peopleRange = i.toString(), fillColor = i)
        }
    } else {
        var startNum = 1
        var endNum = participants / 3
        NumberBlock(peopleRange = "0", fillColor = 0)

        for (i in 1..3) {
            if (startNum == endNum) NumberBlock(peopleRange = "$startNum", fillColor = i)
            else NumberBlock(peopleRange = "$startNum-$endNum", fillColor = i)

            startNum = endNum + 1
            if (i == 2) endNum = participants
            else endNum *= 2
        }
    }
}

@Composable
fun NumberBlock( //숫자 블록 하나
    peopleRange: String,
    fillColor: Int
) {
    val backgroundColor: Int

    if (fillColor == 0) backgroundColor = R.color.white
    else if (fillColor == 1) backgroundColor = R.color.blue3
    else if (fillColor == 2) backgroundColor = R.color.blue1
    else backgroundColor = R.color.main_blue

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(33.dp)
                .background(color = colorResource(id = backgroundColor))
                .border(width = 1.dp, color = colorResource(id = R.color.gray_100))
        )
        Spacer(modifier = Modifier.padding(6.dp))
        Text(
            text = peopleRange,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            fontFamily = Pretendard,
            fontWeight = FontWeight(400),
            color = colorResource(id = R.color.gray_500),
        )
    }

}