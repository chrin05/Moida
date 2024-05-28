package com.example.moida

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.moida.R
import com.example.moida.screen.AuthUtils
import com.example.moida.screen.AuthUtils.errorMessages
import com.example.moida.screen.AuthUtils.getErrorMessage
import com.example.moida.ui.theme.MoidaTheme
import com.example.moida.ui.theme.Pretendard
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@Composable
fun JoinMemberShip() {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var isIdFocused by remember { mutableStateOf(false) }
    var isPasswordFocused by remember { mutableStateOf(false) }
    var isNameFocused by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val auth = remember { FirebaseAuth.getInstance() }
    val errorMessage = AuthUtils.errorMessage
    val db = Firebase.firestore

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
                text = "회원가입",
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
                text = "로그인 정보를 입력해주세요",
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
                    onValueChange = { id = it },
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
                    onValueChange = { password = it },
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
                text = "이름을 입력해주세요",
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
                    value = name,
                    onValueChange = { name = it },
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

        errorMessage.value?.let {
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
                if (id.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                    auth.createUserWithEmailAndPassword(id, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                val userId = user?.uid
                                val userData = hashMapOf(
                                    "name" to name,
                                    "email" to id
                                )
                                if (userId != null) {
                                    db.collection("users").document(userId).set(userData)
                                        .addOnSuccessListener {
                                            Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                                            errorMessage.value = null
                                        }
                                        .addOnFailureListener { e ->
                                            errorMessage.value = "회원가입 성공, 하지만 Firestore에 저장 실패: ${e.message}"
                                        }
                                }
                            } else {
                                errorMessage.value = AuthUtils.getErrorMessage(task.exception)
                            }
                        }
                } else {
                    errorMessage.value = "모든 필드를 입력해주세요"
                }
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
fun RegistrationScreenPreview() {
    MoidaTheme {
        JoinMemberShip()
    }
}
