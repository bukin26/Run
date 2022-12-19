package com.gmail.bukin26.mg.ui.news

import com.gmail.bukin26.mg.mvi_core.UiState

data class NewsState(
    val isLoading: Boolean,
    val isError: Boolean,
    val errorMessage: String
) : UiState {
    companion object {
        val Empty = NewsState(
            isLoading = false,
            isError = false,
            errorMessage = ""
        )
    }
}
