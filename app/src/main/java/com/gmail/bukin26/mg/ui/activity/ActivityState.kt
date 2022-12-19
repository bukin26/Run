package com.gmail.bukin26.mg.ui.activity

import com.gmail.bukin26.mg.mvi_core.UiState

data class ActivityState(
    val isLoading: Boolean,
    val isError: Boolean,
    val errorMessage: String
) : UiState {
    companion object {
        val Empty = ActivityState(
            isLoading = false,
            isError = false,
            errorMessage = ""
        )
    }
}
