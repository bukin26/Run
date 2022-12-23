package com.gmail.bukin26.mg.ui.news

import com.gmail.bukin26.mg.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    reducer: NewsReducer,
    newsLoadUseCase: NewsLoadUseCase
) : MviViewModel<NewsAction, NewsState, NewsEffect>(
    useCases = setOf(newsLoadUseCase),
    reducer = reducer,
    initialState = NewsState.Empty
) {

    init {
        launch {
            action(NewsAction.LoadNews)
        }
    }
}