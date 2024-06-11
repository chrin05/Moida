package com.example.moida.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moida.R
import com.example.moida.ui.theme.Pretendard
import com.google.android.play.integrity.internal.c

@Composable
fun TextField(
    title: String,
    value: String,
) {
    Column(
        modifier = Modifier
    ) {
        FieldTitle(title = title)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = value,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontFamily = Pretendard,
            fontWeight = FontWeight(400),
            color = colorResource(id = R.color.gray_800),
        )
        
        val lineColor = colorResource(id = R.color.disabled)
        
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
        ) {
            drawLine(
                color = lineColor,
                start = Offset(0f, 10f),
                end = Offset(size.width, 10f),
                strokeWidth = 5f
            )
        }
    }
}

@Composable
fun ParticipantsField() {
    Column {
        FieldTitle(title = "참여자")

    }
}