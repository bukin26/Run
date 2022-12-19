package com.gmail.bukin26.mg.ui.activity

import com.gmail.bukin26.mg.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    reducer: ActivityReducer
) : MviViewModel<ActivityAction, ActivityState, ActivityEffect>(
    useCases = emptySet(),
    reducer = reducer,
    initialState = ActivityState.Empty
) {
}