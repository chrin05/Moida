package com.example.moida.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.DateField
import com.example.moida.component.NameTextField
import com.example.moida.component.TimeField
import com.example.moida.component.Title
import com.example.moida.model.BottomNavItem
import com.example.moida.model.Routes

@Composable
fun CreateMySchedule(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var name by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }
        var time by remember { mutableStateOf("") }

        Title(
            navController = navController,
            route = BottomNavItem.Home.route,
            title = "개인 일정 추가",
            rightBtn = "추가",
            rightColor = R.color.main_blue
        )

        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 40.dp, end = 24.dp)
        ) {
            NameTextField(title = "일정 이름", onValueChange = { name = it })
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            DateField(title = "일정 날짜", onValueChange = { date = it })
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            TimeField(title = "일정 시간", onValueChange = { time = it })
        }
    }
}
