package com.alberto.habits.home.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alberto.habits.core.presentation.HabitButton
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeAskPermission(
    permission: String,
    modifier: Modifier = Modifier
) {
    val permissionState =
        rememberPermissionState(permission = permission)
    LaunchedEffect(key1 = Unit) {
        permissionState.launchPermissionRequest()
    }

    if (permissionState.status.shouldShowRationale) {
        AlertDialog(
            onDismissRequest = { },
            modifier = modifier,
            confirmButton = {
                HabitButton(
                    text = "Accept",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    permissionState.launchPermissionRequest()
                }
            },
            title = {
                Text(text = "Permission Required")
            },
            text = {
                Text(text = "We need this permission for the app to work correctly")
            }
        )
    }
}