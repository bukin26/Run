package com.gmail.bukin26.mg.ui.news

import com.gmail.bukin26.mg.data.NewsUiModel
import com.gmail.bukin26.mg.mvi_core.UiAction

sealed class NewsAction : UiAction {
    object LoadNews : NewsAction()
    data class LoadingSuccess(val news: List<NewsUiModel>) : NewsAction()
    data class LoadingError(val errorText: String) : NewsAction()
}
