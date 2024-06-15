package com.example.moida.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.model.Meeting
import com.example.moida.model.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMeetingsScreen(
    navController: NavHostController,
    meetings: List<Meeting>,
    isFabMenuExpanded: Boolean,
    onFabMenuToggle: () -> Unit,
    onJoinMeeting: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("나의 모임", fontWeight = FontWeight.Bold) },
                    modifier = Modifier.padding(top = 16.dp)
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onFabMenuToggle() },
                    shape = CircleShape,
                    containerColor = colorResource(id = R.color.main_blue),
                    modifier = Modifier.zIndex(3f)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isFabMenuExpanded) R.drawable.baseline_close_24
                            else R.drawable.baseline_add_24
                        ),
                        contentDescription = "Toggle Menu",
                        tint = colorResource(id = R.color.white)
                    )
                }
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    "전체", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                MeetingGrid(meetings = meetings)
            }
        }

        if (isFabMenuExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
                    .clickable { onFabMenuToggle() }
                    .zIndex(1f)
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 100.dp, end = 16.dp)
                    .zIndex(2f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("모임 만들기", color = Color.White, modifier = Modifier.padding(end = 8.dp))
                    FloatingActionButton(
                        onClick = {
                            onFabMenuToggle()
                            navController.navigate(Routes.CreateMeeting.route)
                        },
                        shape = CircleShape,
                        containerColor = colorResource(id = R.color.white)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "Create Meeting",
                            tint = colorResource(id = R.color.main_blue)
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("모임 참여", color = Color.White, modifier = Modifier.padding(end = 8.dp))
                    FloatingActionButton(
                        onClick = {
                            onFabMenuToggle()
                            onJoinMeeting()
                        },
                        shape = CircleShape,
                        containerColor = colorResource(id = R.color.white)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_add_24),
                            contentDescription = "Join Meeting",
                            tint = colorResource(id = R.color.main_blue)
                        )
                    }
                }
            }

            FloatingActionButton(
                onClick = { onFabMenuToggle() },
                shape = CircleShape,
                containerColor = colorResource(id = R.color.main_blue),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 16.dp)
                    .zIndex(3f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "Close Menu",
                    tint = colorResource(id = R.color.white)
                )
            }


        }
    }
}

@Composable
fun MeetingGrid(meetings: List<Meeting>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(meetings.size) { index ->
            MeetingCard(meeting = meetings[index])
        }
    }
}

@Composable
fun MeetingCard(meeting: Meeting) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RectangleShape)
            .padding(3.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = meeting.imageRes),
                contentDescription = meeting.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = meeting.name,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}