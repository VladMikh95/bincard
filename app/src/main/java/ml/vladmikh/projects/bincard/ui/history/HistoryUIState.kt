package ml.vladmikh.projects.bincard.ui.history

import ml.vladmikh.projects.bincard.data.model.CardInfo
import ml.vladmikh.projects.bincard.utils.ErrorLoadingCard

sealed interface HistoryUIState {

    object Initial: HistoryUIState
    object Loading : HistoryUIState
    data class Loaded(val cards: List<CardInfo>) : HistoryUIState
    data class Error(val error: ErrorLoadingCard) : HistoryUIState
}