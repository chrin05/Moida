package com.example.moida.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moida.R
import com.example.moida.model.GroupInfo
import com.example.moida.ui.theme.Pretendard

@Composable
fun Title(title: String, rightBtn: String, rightColor: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_left_shevron),
            contentDescription = "뒤로가기",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = title,
            fontFamily = Pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = rightBtn,
            fontFamily = Pretendard,
            fontWeight = FontWeight.Bold,
            color = Color(rightColor),
            fontSize = 18.sp
        )
    }
}

@Composable
fun HomeTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.moida_logo),
            contentDescription = "로고"
            )

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .wrapContentSize(),
            border = BorderStroke(1.dp, colorResource(id = R.color.main_blue)),
        ) {
            Text(
                text = "개인 일정 추가",
                fontFamily = Pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.text_high)
            )
        }
    }
}

@Composable
fun GroupDetailTitle(group: GroupInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 37.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 6.dp),
                text = group.groupName,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = colorResource(id = R.color.text_high)
                )

            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .wrapContentSize(),
                border = BorderStroke(1.dp, colorResource(id = R.color.main_blue)),
            ) {
                Text(
                    text = "일정 잡기",
                    fontFamily = Pretendard,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.text_high)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "사람 아이콘",
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp),
                tint = colorResource(id = R.color.disabled)
            )
            Text(
                text = "${group.memberCount}명",
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = colorResource(id = R.color.disabled),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
@Preview
fun TitlePreview() {
    GroupDetailTitle(group = GroupInfo("Compose 스터디", 4))
}