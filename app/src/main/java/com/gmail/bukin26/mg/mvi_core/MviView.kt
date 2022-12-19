package com.gmail.bukin26.mg.mvi_core

interface MviView<State : UiState> {
    fun render(state: State)
}