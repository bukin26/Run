package com.gmail.bukin26.mg.ui.run

import com.gmail.bukin26.mg.mvi_core.Reducer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RunReducer @Inject constructor() : Reducer<RunState, RunAction> {
    override suspend fun invoke(state: RunState, action: RunAction): RunState {
        TODO("Not yet implemented")
    }
}