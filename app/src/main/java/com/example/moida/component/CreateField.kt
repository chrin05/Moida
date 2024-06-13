package com.example.moida.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moida.R
import com.example.moida.ui.theme.Pretendard


@Composable
fun FieldTitle(
    title: String
) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 2.52.sp,
            fontFamily = Pretendard,
            fontWeight = FontWeight(700),
            color = colorResource(id = R.color.gray_300),
        )
    )
}

@Composable
fun NameTextField(
    title: String,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        var name by remember { mutableStateOf("") }
        var isFocused by remember { mutableStateOf(false) }

        FieldTitle(title = title)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            InputEditText(
                value = name,
                modifier = Modifier.onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
                onValueChange = {
                    name = it
                    onValueChange(name)
                }
            )
            if (name.isNotEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_clear_button),
                    contentDescription = "모두지우기",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { name = "" }
                )
            } else {
                //입력이 안되어 있을 땐 아이콘 안보이게
            }
        }
        val lineColor = if (isFocused) colorResource(id = R.color.main_blue) else colorResource(id = R.color.gray_300)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
        ) {
            drawLine(
                color = lineColor,
                start = Offset(0f, 10f),
                end = Offset(size.width, 10f),
                strokeWidth = 5f
            )
        }
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun InputEditText(
    value: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    placeHolderString: String = "일정 이름 입력",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TextStyle(
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontFamily = Pretendard,
            fontWeight = FontWeight(400),
            color = colorResource(id = R.color.gray_800),
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeHolderString,
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 22.sp,
                            fontFamily = Pretendard,
                            fontWeight = FontWeight(400),
                            color = colorResource(id = R.color.disabled),
                        ),
                    )
                }

                innerTextField()

            }
        },
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = 1,
        cursorBrush = SolidColor(Color(R.color.main_blue))
    )
}

@Composable
fun DateField(
    title: String,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        var date by remember { mutableStateOf("") }

        FieldTitle(title = title)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (date.isEmpty()) {
                Text(
                    text = "YYYY.MM.DD",
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = Pretendard,
                    fontWeight = FontWeight(400),
                    color = colorResource(id = R.color.disabled),
                )
            } else {
                Text(
                    text = date,
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = Pretendard,
                    fontWeight = FontWeight(400),
                    color = colorResource(id = R.color.gray_800),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "모두지우기",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { }
            )
        }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
        ) {
            drawLine(
                color = Color.Gray,
                start = Offset(0f, 10f),
                end = Offset(size.width, 10f),
                strokeWidth = 5f
            )
        }
    }
}

@Composable
fun TimeField(
    title: String,
    onValueChange: (String) -> Unit
) {
    var time by remember { mutableStateOf("") }

    FieldTitle(title = title)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        if (time.isEmpty()) {
            Text(
                text = "HH:mm",
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontFamily = Pretendard,
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.disabled),
            )
        } else {
            Text(
                text = time,
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontFamily = Pretendard,
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.gray_800),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = "모두지우기",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(16.dp)
                .clickable { }
        )
    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp)
    ) {
        drawLine(
            color = Color.Gray,
            start = Offset(0f, 10f),
            end = Offset(size.width, 10f),
            strokeWidth = 5f
        )
    }
}