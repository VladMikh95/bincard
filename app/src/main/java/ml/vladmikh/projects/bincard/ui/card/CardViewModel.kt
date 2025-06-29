package ml.vladmikh.projects.bincard.ui.card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ml.vladmikh.projects.bincard.data.repository.CardInfoRepository
import ml.vladmikh.projects.bincard.utils.ErrorLoadingCard
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val repository: CardInfoRepository
) : ViewModel(){

    var bin by mutableStateOf("")
        private set

    fun updateBin(input: String) {
        bin = input
    }

    private val _uiState = MutableStateFlow<CardUIState>(CardUIState.Initial)
    val uiState: StateFlow<CardUIState> = _uiState

    fun isCorrectInputText(text: String): Boolean {

        var result = false

        if(text.length in 6..8 && text.all { it in '0'..'9' }) {
            result = true
        }
        return result
    }

    fun getCardInfo() {

        if (isCorrectInputText(bin)) {
            viewModelScope.launch {

                _uiState.value = CardUIState.Loading

                try {
                    val cardInfo = repository.getCardInfo(bin.toInt())
                    _uiState.value = CardUIState.Loaded(cardInfo)
                } catch (e: IOException) {
                    _uiState.value = CardUIState.Error(ErrorLoadingCard.CONNECTION_ERROR)
                } catch (e: Exception) {
                    _uiState.value = CardUIState.Error(ErrorLoadingCard.ERROR_UNKNOWN)
                }

            }
        } else {
            _uiState.value = CardUIState.Error(ErrorLoadingCard.UNCORRECT_TEXT_LENGTH)
        }
    }
}