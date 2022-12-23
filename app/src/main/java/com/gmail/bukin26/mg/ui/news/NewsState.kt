package com.gmail.bukin26.mg.ui.news

import com.gmail.bukin26.mg.data.NewsUiModel
import com.gmail.bukin26.mg.mvi_core.UiState

data class NewsState(
    val isLoading: Boolean,
    val news: List<NewsUiModel>
) : UiState {
    companion object {
        val Empty = NewsState(
            isLoading = false,
            news = emptyList()
        )
    }
}
