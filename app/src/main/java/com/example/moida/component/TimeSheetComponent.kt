package com.example.moida.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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