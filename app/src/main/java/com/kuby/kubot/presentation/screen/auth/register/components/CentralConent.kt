package com.kuby.kubot.presentation.screen.auth.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kuby.kubot.R
import com.kuby.kubot.presentation.components.CustomTextField
import com.kuby.kubot.presentation.components.DButton
import com.kuby.kubot.presentation.components.TextFieldPass
import com.kuby.kubot.presentation.screen.auth.register.RegisterViewModel
import com.kuby.kubot.presentation.ui.theme.RegularFont

@Composable
fun CentralConent(
    vm: RegisterViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier
            //.fillMaxSize()
            .background(Color.Transparent)
            .padding(top = 200.dp, start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Register",
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            color = Color.Black,
            fontFamily = RegularFont
        )
        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            text = "Enter your access credentials",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
            fontFamily = RegularFont
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextField(
                modifier = Modifier.weight(1f),
                keyboardType = KeyboardType.Text,
                textValue = vm.state.name,
                icon = Icons.Outlined.Face,
                onValueChange = { vm.onNameInput(it) },
                placeholderText = "Name"
            )

            Spacer(modifier = Modifier.width(10.dp))

            CustomTextField(
                modifier = Modifier.weight(1f),
                keyboardType = KeyboardType.Text,
                textValue = vm.state.lastName,
                icon = Icons.Outlined.Favorite,
                onValueChange = { vm.onLastNameInput(it) },
                placeholderText = "Lastname"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number,
            textValue = vm.state.telephone,
            icon = Icons.Outlined.Call,
            onValueChange = { vm.onPhoneInput(it) },
            placeholderText = "Telephone"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email,
            textValue = vm.state.email,
            onValueChange = { vm.onEmailInput(it) },
            placeholderText = "Email"
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextFieldPass(
            modifier = Modifier.fillMaxWidth(),
            textValue = vm.state.password,
            onValueChange = { vm.onPasswordInput(it) },
            showPassword = vm.state.showPassword,
            onTogglePasswordVisibility = { vm.onShowPassword(!vm.state.showPassword) },
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextFieldPass(
            modifier = Modifier.fillMaxWidth(),
            textValue = vm.state.confirmPassword,
            onValueChange = { vm.onConfirmPasswordInput(it) },
            showPassword = vm.state.showConfirmPassword,
            onTogglePasswordVisibility = { vm.onShowConfirmPassword(!vm.state.showConfirmPassword) },
        )

        DButton(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 60.dp, end = 60.dp),
            primaryText = "Login",
            icon = R.drawable.ic_arrow_right,
            onClick = { vm.login() }
        )
    }
}