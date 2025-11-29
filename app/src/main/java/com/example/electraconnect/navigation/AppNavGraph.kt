package com.example.electraconnect.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.electraconnect.presentation.login.LoginScreen
import com.example.electraconnect.presentation.login.SetNewPasswordScreen
import com.example.electraconnect.viewmodel.FirstLoginViewModel
import com.example.electraconnect.viewmodel.LoginViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {

            val viewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                onLoginClick = {
                    viewModel.login(
                        onFirstLogin = { email, temp ->
                            navController.navigate(
                                Screen.SetNewPassword.pass(email, temp)
                            )
                        },
                        onSuccess = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    )
                },
                onForgotPassword = {},
                onRegisterClick = {}
            )
        }


        composable(
            route = Screen.SetNewPassword.route
        ) { navBackStack ->

            val email = navBackStack.arguments?.getString("email") ?: ""
            val temp = navBackStack.arguments?.getString("temp") ?: ""

            val viewModel: FirstLoginViewModel = hiltViewModel()

            // preload email + temp password
            LaunchedEffect(Unit) {
                viewModel.preload(email, temp)
            }

            SetNewPasswordScreen(
                viewModel = viewModel,
                onSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Dummy Home
        composable(Screen.Home.route) {
            androidx.compose.material3.Text(text = "Home Screen")
        }
    }
}
