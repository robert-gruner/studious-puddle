package org.example.kmp.pets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Badge
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun PetCardDetail(
    pet: Pet,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(8.dp),
        ) {

            AsyncImage(
                model = pet.photoUrls[0],
                contentDescription = pet.name,
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pet.name,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = pet.category.name,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                color = MaterialTheme.colors.onSurface.copy(0.6f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Badge(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = pet.status.name,
                        fontSize = 12.sp,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                }
            }
        }
    }
}

