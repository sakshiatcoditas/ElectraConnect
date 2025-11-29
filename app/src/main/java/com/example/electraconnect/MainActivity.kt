package com.example.electraconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.electraconnect.navigation.AppNavGraph
import com.example.electraconnect.presentation.login.LoginScreen
import com.example.electraconnect.presentation.login.SetNewPasswordScreen
import com.example.electraconnect.ui.theme.ElectraConnectTheme
import com.example.electraconnect.viewmodel.FirstLoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElectraConnectTheme {

             val navController = rememberNavController()
                AppNavGraph(navController)


            }
        }
    }
}



