package com.gmail.bukin26.mg.ui.news

import com.gmail.bukin26.mg.data.NewsRepository
import com.gmail.bukin26.mg.data.Result
import com.gmail.bukin26.mg.mvi_core.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NewsLoadUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : UseCase<NewsAction, NewsState, NewsEffect>() {
    override suspend fun invoke(
        actions: Flow<NewsAction>,
        states: Flow<NewsState>
    ): Flow<NewsAction> {
        return actions.filterIsInstance<NewsAction.LoadNews>()
            .mapLatest {
                when (val result = newsRepository.getNews()) {
                    is Result.Success -> NewsAction.LoadingSuccess(result.data)
                    //rework error message
                    is Result.Error -> NewsAction.LoadingError(result.throwable.message.toString())
                }
            }
    }
}