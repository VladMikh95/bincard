package ml.vladmikh.projects.bincard.ui.card

import ml.vladmikh.projects.bincard.data.model.CardInfo
import ml.vladmikh.projects.bincard.utils.ErrorLoadingCard

sealed interface CardUIState {

    object Initial: CardUIState
    object Loading : CardUIState
    data class Loaded(val cardInfo: CardInfo) : CardUIState
    data class Error(val error: ErrorLoadingCard) : CardUIState
}