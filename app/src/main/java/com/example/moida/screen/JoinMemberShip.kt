package com.example.moida.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moida.R
import com.example.moida.model.JoinMembershipViewModel
import com.example.moida.model.Routes
import com.example.moida.ui.theme.MoidaTheme
import com.example.moida.ui.theme.Pretendard

@Composable
fun JoinMembership(navController: NavHostController, viewModel: JoinMembershipViewModel = viewModel()) {
    val id by viewModel.id.collectAsState()
    val password by viewModel.password.collectAsState()
    val name by viewModel.name.collectAsState()
    val errorMessage by AuthUtils.errorMessage.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var isIdFocused by remember { mutableStateOf(false) }
    var isPasswordFocused by remember { mutableStateOf(false) }
    var isNameFocused by remember { mutableStateOf(false) }
    val signUpSuccess by viewModel.signUpSuccess.collectAsState()

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess) {
            navController.navigate(Routes.SignIn.route) {
                popUpTo(Routes.LaunchPage.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {navController.navigate(Routes.LaunchPage.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }},
                modifier = Modifier.weight(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Back"
                )
            }
            Text(
                text = "회원가입",
                fontFamily = Pretendard,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(6f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Divider(
            color = colorResource(id = R.color.main_blue),
            thickness = 4.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 16.dp, 16.dp)
        ) {
            Text(
                text = "로그인 정보를 입력해주세요.",
                fontFamily = Pretendard,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 12.dp)
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

            Text(
                text = "이름을 입력해주세요.",
                fontFamily = Pretendard,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BasicTextField(
                    value = name,
                    onValueChange = { viewModel.onNameChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 16.dp, 8.dp, 8.dp)
                        .onFocusChanged { isNameFocused = it.isFocused },
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
                if (name.isEmpty() && !isNameFocused) {
                    Text(
                        text = "이름 입력",
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
                viewModel.signUp()
            },
            enabled = id.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (id.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                    colorResource(id = R.color.main_blue)
                } else colorResource(id = R.color.gray_200)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = "회원가입 완료하기",
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
fun JoinMembershipPreview() {
    MoidaTheme {
        JoinMembership(navController = rememberNavController())
    }
}
