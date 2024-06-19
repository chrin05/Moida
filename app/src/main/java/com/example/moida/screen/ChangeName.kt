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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.moida.model.schedule.ShareViewModel
import com.example.moida.ui.theme.Pretendard
import com.example.moida.util.SharedPreferencesHelper
import com.google.common.io.Files.append
import kotlinx.coroutines.launch

@Composable
fun ChangeName(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory(context))
    val userName by viewModel.userName.collectAsState()
    var name by remember { mutableStateOf(userName ?: "") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(userName) {
        name = userName ?: ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Title(
            navController = navController,
            route = BottomNavItem.Home.route,
            title = "이름변경",
            rightBtn = "    ",
            rightColor = R.color.main_blue
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            Modifier.background(Color.White)
                .padding(start = 24.dp, end = 16.dp)
        ) {
            //NameTextField(shareViewModel, title = "이름", onValueChange = { name = it }, "이름 입력") 수정필요
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                scope.launch {
                    val result = viewModel.updateUserName(name)
                    if (result) {
                        // 변경 성공 네비게이션 스택을 초기화하고 ChangedName 이동
                        navController.navigate(Routes.ChangedName.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    } else {
                        // 닉네임 변경 실패
                    }
                }
            },
            enabled = name.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (name.isNotEmpty()) {
                    colorResource(id = R.color.main_blue)
                } else colorResource(id = R.color.gray_200)
            ),
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
fun ChangeNamePreview() {
    MoidaTheme {
        ChangeName(navController = rememberNavController())
    }
}

