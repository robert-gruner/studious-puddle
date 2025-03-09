package org.example.kmp.pets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun PetList(onItemClick: (id: Int) -> Unit) {
    val petsApi = PetsApiClient()
    val scope = rememberCoroutineScope()
    var pets by remember { mutableStateOf<List<Pet>>(emptyList()) }

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
            PetCardList(pet = it, onCardClick = { onItemClick(it.id) })
        }
    }


}