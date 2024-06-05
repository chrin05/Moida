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
import com.example.moida.R
import com.example.moida.ui.theme.MoidaTheme
import com.example.moida.ui.theme.Pretendard
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

@Composable
fun SignIn() {
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
    var userName by remember { mutableStateOf<String?>(null) }
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
                if (id.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(id, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                if (user != null) {
                                    db.collection("users").document(user.uid).get()
                                        .addOnSuccessListener { document ->
                                            if (document != null) {
                                                userName = document.getString("name")
                                                Toast.makeText(context, "로그인 성공, ${userName}", Toast.LENGTH_SHORT).show()
                                                errorMessage.value = null
                                            } else {
                                                errorMessage.value = "사용자 정보를 가져올 수 없습니다."
                                            }
                                        }
                                        .addOnFailureListener { e ->
                                            errorMessage.value = "사용자 정보를 가져오는 데 실패했습니다: ${e.message}"
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
fun RegistrationScreenPreview2() {
    MoidaTheme {
        SignIn()
    }
}
