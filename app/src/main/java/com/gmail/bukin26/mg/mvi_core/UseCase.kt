package com.gmail.bukin26.mg.mvi_core

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class UseCase<Action : UiAction, State : UiState, Effect : UiEffect> {

    private val effectChannel = Channel<Effect>()
    val effectFlow: Flow<Effect> = effectChannel.receiveAsFlow()

    abstract suspend operator fun invoke(
        actions: Flow<Action>,
        states: Flow<State>
    ): Flow<Action>

    protected suspend fun sendEffect(effect: Effect) = effectChannel.send(effect)
}