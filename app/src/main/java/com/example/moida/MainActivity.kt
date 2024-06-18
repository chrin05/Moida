package com.example.moida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.moida.screen.TimeInput
import com.example.moida.ui.theme.MoidaTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            MoidaTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
//                    val navBackStackEntry by navController.currentBackStackEntryAsState()
//                    val newScheduleViewModel = NewScheduleViewModel(application = Application())
//                    val currentRoute = navBackStackEntry?.destination?.route
//                    Scaffold(
//                        modifier = Modifier.fillMaxSize(),
//                        bottomBar = {
//                            if (currentRoute !in listOf(
//                                    "launchPage",
//                                    "signIn",
//                                    "joinMembership",
//                                    "ResignMemberShip"
//                                )
//                            ) {
//                                NavigationBar(
//                                    containerColor = colorResource(id = R.color.white),
//                                    tonalElevation = 10.dp
//                                ) {
//                                    BottomNavigationItem().bottomNavigationItems()
//                                        .forEachIndexed { _, item ->
//                                            NavigationBarItem(
//                                                selected = item.route == currentRoute,
//                                                label = {
//                                                    Text(
//                                                        text = stringResource(item.label),
//                                                        fontFamily = Pretendard,
//                                                        fontWeight = FontWeight.Medium,
//                                                        fontSize = 12.sp
//                                                    )
//                                                },
//                                                onClick = {
//                                                    navController.navigate(item.route) {
//                                                        popUpTo(navController.graph.findStartDestination().id) {
//                                                            saveState = true
//                                                        }
//                                                        launchSingleTop = true
//                                                        restoreState = true
//                                                    }
//                                                },
//                                                icon = {
//                                                    Icon(
//                                                        painterResource(id = item.icon),
//                                                        contentDescription = item.label.toString()
//                                                    )
//                                                },
//                                                colors = NavigationBarItemDefaults.colors(
//                                                    selectedIconColor = colorResource(id = R.color.main_blue),
//                                                    unselectedIconColor = colorResource(id = R.color.disabled),
//                                                    selectedTextColor = colorResource(id = R.color.main_blue),
//                                                    unselectedTextColor = colorResource(id = R.color.disabled)
//                                                )
//                                            )
//                                        }
//                                }
//                            }
//                        }
//                    ) {
//                        Box(
//                            modifier = Modifier.padding(it)
//                        ) {
//                            NavGraph(
//                                navController = navController,
//                            )
//                        }
//                    }
//                }
//                GroupDetail();
                TimeInput(navController = rememberNavController());
            }
        }
    }
}
