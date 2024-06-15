package com.example.moida.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.moida.ui.theme.MoidaTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.component.BottomBtn
import com.example.moida.component.DateField
import com.example.moida.component.FieldTitle
import com.example.moida.component.NameTextField
import com.example.moida.component.Title
import com.example.moida.model.BottomNavItem
import com.example.moida.model.Routes
import com.example.moida.ui.theme.Pretendard
import com.example.moida.util.SharedPreferencesHelper
import com.google.common.io.Files.append

@Composable
fun ResignMemberShip(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .padding(vertical = 30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "회원탈퇴 체크이미지",
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "회원탈퇴가 완료되었습니다.",
                    fontFamily = Pretendard,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.text_high)
                )
                Text(
                    text = "그동안 모이다 서비스를 이용해주셔서 감사합니다.",
                    fontFamily = Pretendard,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.text_high)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                // 네비게이션 스택을 초기화하고 LaunchPage 이동
                navController.navigate(Routes.LaunchPage.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.main_blue)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = "확인",
                color = Color.White,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResignMemberShip() {
    MoidaTheme {
        ResignMemberShip(navController = rememberNavController())
    }
}

