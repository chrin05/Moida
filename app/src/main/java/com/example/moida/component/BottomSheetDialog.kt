package com.example.moida.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.model.schedule.ShareViewModel
import com.example.moida.ui.theme.Pretendard
import java.time.format.DateTimeFormatter

//@Composable
//fun CalendarBottomSheet(
//    navController: NavHostController,
//    shareViewModel: ShareViewModel,
//) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        CalendarBottomSheetDialog(navController, shareViewModel)
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarBottomSheet(
    navController: NavHostController,
) {
    var selectedDate by remember { mutableStateOf("") }
    val modalBottomSheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ModalBottomSheet(
            onDismissRequest = { },
            sheetState = modalBottomSheetState,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            containerColor = colorResource(id = R.color.white),
            dragHandle = { BottomSheetDefaults.DragHandle()},
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 30.dp)
            ) {

                BottomSheetCalendar { selectedDate = it.toString() }

                Button(
                    onClick = {
                        //onDismiss()값 넘기기
                        selectedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                        selectedDate?.let { date->
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("selectedDate", date)
                            navController.popBackStack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (true) {
                            colorResource(id = R.color.main_blue)
                        } else colorResource(id = R.color.gray_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "적용하기",
                        color = Color.White,
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }

            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewBottomSheetDialog(){
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        CalendarBottomSheetDialog(onDismiss = {})
//
//    }
//
//}