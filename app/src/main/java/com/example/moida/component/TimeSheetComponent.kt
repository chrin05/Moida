package com.example.moida.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import java.util.Date


@Composable
fun HeadOfTime(
    startDate: String,
    startDay: Int
) {
    val dateFormatter = DateTimeFormatter.ofPattern("MM.dd")
    var currentDate = LocalDate.parse("2024.05.05", DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    var currentDay = DayOfWeek.MONDAY

    Column {
        for (i in 0 until 3) {
            DateOfTimeSheet(
                date = currentDate.format(dateFormatter),
                day = currentDay.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault())
            )

            currentDate = currentDate.plusDays(1)
            currentDay = currentDay.plus(1)
        }
    }
}

@Composable
fun DateOfTimeSheet(
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

    Row {
        Text(text = date, style = textStyle)
        Text(text = day, style = textStyle)
    }
}

@Composable
fun NumberBlock(
    peopleRange: String,
    fillColor: Int
) {
    Row {
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(33.dp)
                .background(color = colorResource(id = fillColor))
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