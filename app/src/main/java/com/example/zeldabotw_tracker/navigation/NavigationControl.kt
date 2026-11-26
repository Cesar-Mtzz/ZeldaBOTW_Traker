package com.example.zeldabotw_tracker.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zeldabotw_tracker.data.database.users.UserViewModel
import com.example.zeldabotw_tracker.screens.userviews.HomeView
import com.example.zeldabotw_tracker.screens.userviews.LoginForm
import com.example.zeldabotw_tracker.screens.userviews.RegisterForm

@Composable
fun NavigationControl() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable(route = "Login") {
            LoginForm(
                userViewModel = userViewModel,
                onLogin = { user, pass ->
                    userViewModel.login(user, pass) {
                        navController.navigate("Home") {
                            popUpTo("Login") {
                                inclusive = true
                            }
                        }
                    }
                },
                onRegister = {
                    navController.navigate(route = "Register")
                }
            )
        }
        composable(route = "Register") {
            RegisterForm(
                onSave = { user ->
                    userViewModel.save(user) {
                        navController.popBackStack()
                    }
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "Home") {
            val userId = userViewModel.currentUserId
            if (userId != null) {
                HomeView(userId = userId)
            }
        }
    }
}
