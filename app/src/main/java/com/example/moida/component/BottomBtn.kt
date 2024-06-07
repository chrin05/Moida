package com.example.moida.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.contentcapture.ContentCaptureManager.Companion.isEnabled
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.model.Routes
import com.example.moida.ui.theme.Pretendard
import org.checkerframework.common.subtyping.qual.Bottom

@Composable
fun BottomBtn(
    navController: NavController,
    route: String,
    btnName: String,
    activate: Boolean,

) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        onClick = { navController.navigate(route) }, //navController.navigate(route)
        colors = ButtonDefaults.buttonColors(
            containerColor = if (activate) colorResource(id = R.color.main_blue) else colorResource(
                id = R.color.disabled
            ),
        )
    ) {
        Text(
            text = btnName,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = Pretendard,
            fontWeight = FontWeight(500),
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            )
    }

}