package com.gmail.bukin26.mg.ui.news

import com.gmail.bukin26.mg.mvi_core.Reducer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsReducer @Inject constructor() : Reducer<NewsState, NewsAction> {
    override suspend fun invoke(state: NewsState, action: NewsAction): NewsState {
        TODO("Not yet implemented")
    }
}