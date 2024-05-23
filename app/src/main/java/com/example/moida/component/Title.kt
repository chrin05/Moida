package com.example.moida.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moida.R

@Composable
fun Title(title: String, rightBtn: String, rightColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_left_shevron), contentDescription = "뒤로가기",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = rightBtn,
            fontWeight = FontWeight.Bold,
            color = rightColor
        )
    }
}