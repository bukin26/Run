package com.gmail.bukin26.mg.ui.news

import com.gmail.bukin26.mg.mvi_core.Reducer

class NewsReducer : Reducer<NewsState, NewsAction> {
    override suspend fun invoke(state: NewsState, action: NewsAction): NewsState {
        TODO("Not yet implemented")
    }
}