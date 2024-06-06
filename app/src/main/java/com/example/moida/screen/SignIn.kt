package com.example.moida.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moida.R
import com.example.moida.ui.theme.MoidaTheme
import com.example.moida.ui.theme.Pretendard

@Composable
fun SignIn(viewModel: SignInViewModel = viewModel()) {
    val context = LocalContext.current

    // 초기화 메서드 호출
    LaunchedEffect(viewModel) {
        viewModel.initialize(context)
    }

    val id by viewModel.id.collectAsState()
    val password by viewModel.password.collectAsState()
    val userName by viewModel.userName.collectAsState()
    val errorMessage by AuthUtils.errorMessage.collectAsState()
    val lastLoggedOutEmail by viewModel.lastLoggedOutEmail.collectAsState()
    val focusManager = LocalFocusManager.current
    var isIdFocused by remember { mutableStateOf(false) }
    var isPasswordFocused by remember { mutableStateOf(false) }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(lastLoggedOutEmail) {
        lastLoggedOutEmail?.let {
            Toast.makeText(context, "$it 에서 로그아웃했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(userName) {
        userName?.let {
            Toast.makeText(context, "로그인 성공: $it", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp, 48.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* 전 화면으로 돌아가기 */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Back"
                )
            }
            Text(
                text = "로그인",
                fontFamily = Pretendard,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }

        Divider(
            color = colorResource(id = R.color.main_blue),
            thickness = 2.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 30.dp, 16.dp, 16.dp)
        ) {
            Text(
                text = "로그인을 진행합니다.",
                fontFamily = Pretendard,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BasicTextField(
                    value = id,
                    onValueChange = { viewModel.onIdChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 16.dp, 8.dp, 8.dp)
                        .onFocusChanged { isIdFocused = it.isFocused },
                    textStyle = TextStyle(
                        fontFamily = Pretendard,
                        fontSize = 18.sp
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                )
                if (id.isEmpty() && !isIdFocused) {
                    Text(
                        text = "이메일 입력",
                        color = colorResource(id = R.color.gray_200),
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorResource(id = R.color.gray_200))
                        .align(Alignment.BottomCenter)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BasicTextField(
                    value = password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 16.dp, 8.dp, 8.dp)
                        .onFocusChanged { isPasswordFocused = it.isFocused },
                    textStyle = TextStyle(
                        fontFamily = Pretendard,
                        fontSize = 18.sp
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                if (password.isEmpty() && !isPasswordFocused) {
                    Text(
                        text = "비밀번호 입력",
                        color = colorResource(id = R.color.gray_200),
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorResource(id = R.color.gray_200))
                        .align(Alignment.BottomCenter)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = {
                viewModel.signOut()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.main_blue)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = "로그아웃 테스트",
                color = Color.White,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = {
                viewModel.signIn(context = context)
            },
            enabled = id.isNotEmpty() && password.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (id.isNotEmpty() && password.isNotEmpty()) {
                    colorResource(id = R.color.main_blue)
                } else colorResource(id = R.color.gray_200)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = "로그인하기",
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
fun SignInPreview() {
    MoidaTheme {
        SignIn()
    }
}
