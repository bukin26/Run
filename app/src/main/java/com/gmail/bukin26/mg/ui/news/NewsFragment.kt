package com.gmail.bukin26.mg.ui.news

import androidx.fragment.app.viewModels
import com.gmail.bukin26.mg.databinding.FragmentNewsBinding
import com.gmail.bukin26.mg.mvi.MviFragment

class NewsFragment : MviFragment<FragmentNewsBinding, NewsState, NewsViewModel>() {

    override val viewModel: NewsViewModel by viewModels()

    override fun render(state: NewsState) {
    }
}