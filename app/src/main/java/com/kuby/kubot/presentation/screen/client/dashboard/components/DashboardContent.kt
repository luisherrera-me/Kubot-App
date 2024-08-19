package com.kuby.kubot.presentation.screen.client.dashboard.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kuby.kubot.MainActivity
import com.kuby.kubot.presentation.components.GoogleButton
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.screen.client.dashboard.DashboardViewModel

@Composable
fun DashboardContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    vm: DashboardViewModel = hiltViewModel()
) {

    val activity = LocalContext.current as? Activity
    Column(
        modifier = Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxWidth(), // Opcional, si deseas que los elementos ocupen todo el ancho disponible
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        GoogleButton(
            onClick = {
                vm.logout()
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }
        )

        Button(onClick = {
            vm.logout()
            activity?.finish()
            activity?.startActivity(Intent(activity, MainActivity::class.java))
        }) {
            Text(text = "SALIR")
        }
    }
}
