package com.gmail.bukin26.mg.ui.activity

import com.gmail.bukin26.mg.mvi_core.Reducer
import javax.inject.Singleton

@Singleton
class ActivityReducer : Reducer<ActivityState, ActivityAction>{
    override suspend fun invoke(state: ActivityState, action: ActivityAction): ActivityState {
        TODO("Not yet implemented")
    }
}