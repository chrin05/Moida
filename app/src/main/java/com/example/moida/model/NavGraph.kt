package com.example.moida.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moida.R
import com.example.moida.component.CalendarBottomSheet
import com.example.moida.model.schedule.NewScheduleViewModel
import com.example.moida.screen.CreateGroupSchedule
import com.example.moida.screen.CreateMySchedule
import com.example.moida.screen.JoinMembership
import com.example.moida.screen.MainHome
import com.example.moida.screen.MyPage
import com.example.moida.screen.ScheduleDetail
import com.example.moida.screen.TimeInput
import com.example.moida.screen.SignIn
import com.example.moida.screen.TimeSheet
import com.example.moida.screen.LaunchPage

sealed class BottomNavItem(val title: Int, val icon: Int, var route: String) {
    data object Home : BottomNavItem(R.string.home, R.drawable.home, "home")
    data object Group : BottomNavItem(R.string.group, R.drawable.group, "group")
    data object My : BottomNavItem(R.string.my, R.drawable.my, "my")
}

sealed class Routes(val route: String) {
    data object CreateMySchedule : Routes("createMySchedule")
    data object CreateGroupSchedule : Routes("createGroupSchedule")
    data object ScheduleDetail : Routes("scheduleDetail")
    data object TimeSheet : Routes("timeSheet")
    data object TimeInput : Routes("timeInput")
    data object SignIn : Routes("signIn")
    data object JoinMembership : Routes("joinMembership")
    data object LaunchPage : Routes("launchPage")
    data object CalendarBottomSheet : Routes("calendarBottomSheet")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    newScheduleViewModel: NewScheduleViewModel,
) {
    NavHost(navController = navController, startDestination = Routes.LaunchPage.route) {
        composable(BottomNavItem.Home.route) {
            MainHome(navController)
        }
        composable(BottomNavItem.Group.route) {
//            MainGroup()
        }
        composable(BottomNavItem.My.route) {
            MyPage(navController)
        }

        composable(Routes.CreateMySchedule.route) {
            CreateMySchedule(navController)
        }

        composable(Routes.CreateGroupSchedule.route) {
            CreateGroupSchedule(navController)
        }

        composable(Routes.ScheduleDetail.route) {
            ScheduleDetail(navController)
        }

        composable(
            route = Routes.TimeSheet.route + "?scheduleId={scheduleId}",
            arguments = listOf(
                navArgument("scheduleId") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt("scheduleId")?.let { it1 ->
                TimeSheet(
                    navController,
                    newScheduleViewModel,
                    scheduleId = it1,
                )
            }
        }

        composable(
            route = Routes.TimeInput.route
        ) {
            TimeInput(navController)
        }

        composable(Routes.LaunchPage.route) {
            LaunchPage(navController)
        }

        composable(Routes.SignIn.route) {
            SignIn(navController)
        }

        composable(Routes.JoinMembership.route) {
            JoinMembership(navController)
        }

        composable(Routes.CalendarBottomSheet.route) {
            CalendarBottomSheet(navController)
        }
    }
}