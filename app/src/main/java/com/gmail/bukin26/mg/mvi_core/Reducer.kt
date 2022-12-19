package com.gmail.bukin26.mg.mvi_core

interface Reducer<State : UiState, Action : UiAction> {
    suspend operator fun invoke(state: State, action: Action): State
}