package com.gmail.bukin26.mg.ui.run

import com.gmail.bukin26.mg.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RunViewModel @Inject constructor(
    reducer: RunReducer
) : MviViewModel<RunAction, RunState, RunEffect>(
    useCases = emptySet(),
    reducer = reducer,
    initialState = RunState.Empty
) {
}