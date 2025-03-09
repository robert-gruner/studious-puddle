package org.example.kmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val petsApi = PetsApiClient()

    MaterialTheme {
        val scope = rememberCoroutineScope()
        var pets by remember { mutableStateOf<List<Pet>>(emptyList()) }

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
                color = MaterialTheme.colors.primary.copy(0.1f),
            ).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LaunchedEffect(true) {
                scope.launch {
                    pets = petsApi.fetchPets()
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.run { spacedBy(16.dp) }
            ) {
                items(pets) {
                    PetCard(pet = it, onCardClick = {})
                }
            }


        }
    }
}