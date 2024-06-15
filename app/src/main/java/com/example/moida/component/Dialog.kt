package com.example.moida.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.ui.theme.Pretendard

@Composable
fun CustomDialog(
    navController: NavController,
    mainTitle: String,
    content: String,
    leftBtn: String,
    rightBtn: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit
) {
    Dialog(
        onDismissRequest = { onClickLeft() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp), // Card의 모든 꼭지점에 8.dp의 둥근 모서리 적용
        )
        {
            Column(
                modifier = Modifier
                    .width(345.dp)
                    .wrapContentHeight()
                    .background(
                        color = Color.White,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = mainTitle,
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = Pretendard,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF242424),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = content,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontFamily = Pretendard,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF242424),

                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.LightGray)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min) // Row의 높이를 내부 컴포넌트에 맞춤
                ) {
                    Button(
                        onClick = {
                                  navController.popBackStack()
                                  },
                        shape = RectangleShape,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // 버튼 배경색상
                            contentColor = Color.Black, // 버튼 텍스트 색상
                            disabledContainerColor = Color.Gray, // 버튼 비활성화 배경 색상
                            disabledContentColor = Color.White, // 버튼 비활성화 텍스트 색상
                        ),

                        ) {
                        Text(
                            text = leftBtn,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = Pretendard,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6F6F6F),
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(color = Color.LightGray)
                    )

                    Button(
                        onClick = { onClickRight() },
                        shape = RectangleShape,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // 버튼 배경색상
                            contentColor = Color.Red, // 버튼 텍스트 색상
                            disabledContainerColor = Color.Gray, // 버튼 비활성화 배경 색상
                            disabledContentColor = Color.White, // 버튼 비활성화 텍스트 색상
                        ),
                    ) {
                        Text(
                            text = rightBtn,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = Pretendard,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF4579FF),
                        )
                    }
                }
            }
        }
    }
}
