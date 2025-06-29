package ml.vladmikh.projects.bincard.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ml.vladmikh.projects.bincard.data.repository.CardInfoRepository
import ml.vladmikh.projects.bincard.utils.ErrorLoadingCard
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: CardInfoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HistoryUIState>(HistoryUIState.Initial)
    val uiState: StateFlow<HistoryUIState> = _uiState

    init {
        getCards()
    }
    fun getCards() {
        viewModelScope.launch {

            _uiState.value = HistoryUIState.Loading

            try {
                repository.getAllCard().collect { result ->
                    _uiState.value = HistoryUIState.Loaded(result)
                }


            }catch (e: Exception) {
                _uiState.value = HistoryUIState.Error(ErrorLoadingCard.ERROR_UNKNOWN)
            }

        }
    }
}