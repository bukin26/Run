package com.gmail.bukin26.mg.mvi

import com.gmail.bukin26.mg.base.BaseViewModel
import com.gmail.bukin26.mg.mvi_core.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


abstract class MviViewModel<Action : UiAction, State : UiState, Effect : UiEffect>(
    private val reducer: Reducer<State, Action>,
    private val useCases: Set<UseCase<Action, State, Effect>>,
    initialState: State,
) : BaseViewModel() {

    private val mutableStateFlow = MutableStateFlow(initialState)
    val stateFlow: Flow<State> = mutableStateFlow.asStateFlow()

    private val mutableActionFlow = MutableSharedFlow<Action>()

    private val effectChannel = Channel<Effect>()
    val effectFlow = effectChannel.receiveAsFlow()

    init {
        launch {
            mutableActionFlow
                .withLatestFrom(mutableStateFlow) { action, state ->
                    reducer(state, action)
                }
                .distinctUntilChanged()
                .collect {
                    mutableStateFlow.emit(it)
                }
        }

        launch {
            useCases
                .map { useCase ->
                    useCase(mutableActionFlow, mutableStateFlow)
                }
                .merge()
                .collect {
                    mutableActionFlow.emit(it)
                }
        }

        launch {
            useCases
                .map { useCase ->
                    useCase.effectFlow
                }
                .merge()
                .collect {
                    effectChannel.send(it)
                }
        }
    }

    protected fun action(action: Action) {
        launch {
            mutableActionFlow.emit(action)
        }
    }

    protected fun sendEffect(effect: Effect) {
        launch {
            effectChannel.send(effect)
        }
    }
}