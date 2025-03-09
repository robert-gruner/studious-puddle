package org.example.kmp.pets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetCardList(
    pet: Pet,
    onCardClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp),
        elevation = 4.dp,
        backgroundColor = Color.White,
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image (Left side)
            AsyncImage(
                model = pet.photoUrls[0],
                contentDescription = pet.name,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )

            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(80.dp),
                color = Color.LightGray
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = pet.category.name,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    color = Color.Gray
                )

            }
        }
    }
}

