package com.app.moviedb.profile.compose

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.app.moviedb.R
import com.app.moviedb.profile.viewmodel.ProfileViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person


@Composable
fun ProfileScreen(
    innerPadding: androidx.compose.foundation.layout.PaddingValues,
    viewModel: ProfileViewModel = hiltViewModel()
)
{
    val profile by viewModel.profile
    val showDialog = remember { mutableStateOf(false) }
    val newName = remember { mutableStateOf(profile?.userName ?: "") }
    val profileImageUri = remember { mutableStateOf(profile?.profileImagePath ?: "") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            profileImageUri.value = it.toString()
            viewModel.updateProfile(newName.value, it.toString())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .shadow(6.dp)
                .padding(vertical = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (profileImageUri.value.isNotEmpty()) {
                        Image(
                            painter = rememberImagePainter(profileImageUri.value),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = profile?.userName ?: "Enter your name",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (profile?.userName.isNullOrEmpty()) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable { showDialog.value = true }
                )
            }
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Edit Name") },
                text = {
                    OutlinedTextField(
                        value = newName.value,
                        onValueChange = { newName.value = it },
                        label = { Text("Name") }
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.updateProfile(newName.value, profileImageUri.value)
                        showDialog.value = false
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val buttons = listOf(
                Pair("Editar", Icons.Default.Edit),
                Pair("Configuración", Icons.Default.Settings),
                Pair("Notificaciones", Icons.Default.Notifications),
                Pair("Cerrar sesión", Icons.Default.ExitToApp)
            )
            buttons.forEach { (text, icon) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = text,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = text,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Divider(
                    modifier = Modifier.fillMaxWidth(0.75f),
                    color = MaterialTheme.colorScheme.outlineVariant,
                    thickness = 1.dp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            val socialIcons = listOf(
                Icons.Default.Person,
                Icons.Default.Person,
                Icons.Default.Person
            )
            socialIcons.forEach { icon ->
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Social Media",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
