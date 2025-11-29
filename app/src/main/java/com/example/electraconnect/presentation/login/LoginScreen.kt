package com.example.electraconnect.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electraconnect.R

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val gradientBg = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1E88E5),
            Color(0xFF42A5F5),
            Color(0xFF90CAF9)
        )
    )

    // Custom TextField Colors
    val customTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFF1E88E5),
        unfocusedBorderColor = Color(0xFF90CAF9),
        focusedLabelColor = Color(0xFF1E3A8A),
        unfocusedLabelColor = Color(0xFF1E3A8A),
        cursorColor = Color(0xFF1E88E5)

    )

    Box(
        modifier = Modifier

            .fillMaxSize()
            .background(gradientBg)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {

        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.95f)
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //  Login Title
                Text(
                    text = stringResource(id = R.string.login_title),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A8A)
                )

                Spacer(modifier = Modifier.height(24.dp))

                //  Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(id = R.string.email_label)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = customTextFieldColors,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                //  Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(id = R.string.password_label)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = customTextFieldColors,
                    visualTransformation =
                        if (showPassword) VisualTransformation.None
                        else PasswordVisualTransformation(),

                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector =
                                    if (showPassword) Icons.Default.Home
                                    else Icons.Default.Home,
                                contentDescription = stringResource(
                                    id = R.string.toggle_password_content_desc
                                )
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                //  Forgot Password
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    color = Color(0xFF1E88E5),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onForgotPassword() }
                )

                Spacer(modifier = Modifier.height(24.dp))

                //  Login Button
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.login_button_text),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                //  Register Text
                Text(
                    text = stringResource(id = R.string.register_as_customer),
                    color = Color(0xFF1E88E5),
                    fontSize = 15.sp,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
    }
}
