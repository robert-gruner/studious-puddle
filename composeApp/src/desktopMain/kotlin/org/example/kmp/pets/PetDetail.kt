package org.example.kmp.pets

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@Composable
fun PetDetail(id: Int, onBackClick: () -> Unit) {
    val petsApi = PetsApiClient()
    val scope = rememberCoroutineScope()
    var pet by remember { mutableStateOf<Pet?>(null) }

    LaunchedEffect(true) {
        scope.launch {
            pet = petsApi.fetchPet(id)
        }
    }

    Column {
        IconButton(
            onClick = onBackClick,
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
        }

        pet?.let { PetCardDetail(pet = it) }
    }
}