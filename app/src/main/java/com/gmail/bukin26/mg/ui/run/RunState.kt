package com.gmail.bukin26.mg.ui.run

import com.gmail.bukin26.mg.mvi_core.UiState

data class RunState(
    val isLoading: Boolean,
    val isError: Boolean,
    val errorMessage: String
) : UiState {
    companion object {
        val Empty = RunState(
            isLoading = false,
            isError = false,
            errorMessage = ""
        )
    }
}
