package com.gmail.bukin26.mg.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun getNews(): Result<List<NewsUiModel>> {
        return wrapResult {
            val response = newsApi.getNews()
            if (response.status != "ok" || response.data().isNullOrEmpty()) {
                //change error message and maybe divide if check for two error message
                throw RuntimeException("error")
            }
            return@wrapResult response.data()?.map {
                it.toUiModel()
                //change error message
            } ?: throw RuntimeException("error")
        }
    }
}