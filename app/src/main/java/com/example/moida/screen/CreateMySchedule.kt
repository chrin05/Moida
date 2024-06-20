package com.example.moida.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.DateField
import com.example.moida.component.NameTextField
import com.example.moida.component.TimeField
import com.example.moida.component.Title
import com.example.moida.model.BottomNavItem
import com.example.moida.model.schedule.FixedScheduleData
import com.example.moida.model.schedule.FixedScheduleViewModel
import com.example.moida.model.schedule.MyScheduleViewModel
import com.example.moida.model.schedule.index
import com.example.moida.ui.theme.Pretendard
import java.util.Random

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CreateMySchedule(
    navController: NavHostController,
    myScheduleViewModel: MyScheduleViewModel,
    fixedScheduleViewModel: FixedScheduleViewModel,
) {
    val scheduleName by myScheduleViewModel.scheduleName.collectAsState()
    val scheduleDate by myScheduleViewModel.scheduleDate.collectAsState()
    val scheduleTime by myScheduleViewModel.scheduleTime.collectAsState()

    val context = LocalContext.current
    val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory(context))
    val userName = signInViewModel.userName.value

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_left_shevron),
                contentDescription = "뒤로가기",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo(BottomNavItem.Home.route) { inclusive = true }
                        }
                    }
            )
            Text(
                text = "개인 일정 추가",
                fontFamily = Pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "추가",
                fontFamily = Pretendard,
                fontWeight = FontWeight.Bold,
                color =  colorResource(id = R.color.main_blue),
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    index = Random().nextInt(9000) + 1000
                    val res = FixedScheduleData (
                        scheduleId = index,
                        scheduleName = scheduleName,
                        scheduleDate = scheduleDate,
                        scheduleTime = scheduleTime,
                        category = userName.toString()
                    )
                    fixedScheduleViewModel.AddFixedSchedule(res)
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) { inclusive = true }
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 40.dp, end = 24.dp)
        ) {
            NameTextField(title = "일정 이름", name = scheduleName, onValueChange = {
                myScheduleViewModel.changeSName(it)
                }, "일정 이름 입력")
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            DateField(navController, title = "일정 날짜", date = scheduleDate, onValueChange = {
                myScheduleViewModel.changeSDate(it)})
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            TimeField(navController, title = "일정 시간", time = scheduleTime, onValueChange = {
                myScheduleViewModel.changeSTime(it)})
        }
    }
}
