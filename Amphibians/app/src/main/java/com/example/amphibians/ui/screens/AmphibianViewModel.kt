package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.amphibians.model.Amphibian

sealed interface AmphibianUIState {
    data class Success(val items: List<Amphibian>) : AmphibianUIState
    data object Error : AmphibianUIState
    data object Loading : AmphibianUIState
}

class AmphibianViewModel : ViewModel() {
    var amphibianUIState: AmphibianUIState by mutableStateOf(AmphibianUIState.Loading)
        private set

}