package com.app.ui_common.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun PeopleMediaCard(
    id: String? = null,
    profileImageUrl: String,
    name: String,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f)
            .clickable {
                id?.let {}
            }
    ) {
        Column {
            Image(
                painter = rememberImagePainter(profileImageUrl),
                contentDescription = name,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
