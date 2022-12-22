package com.gmail.bukin26.mg.ui.news

import androidx.fragment.app.viewModels
import com.gmail.bukin26.mg.databinding.FragmentNewsBinding
import com.gmail.bukin26.mg.mvi.MviFragment
import com.gmail.bukin26.mg.navigation.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : MviFragment<FragmentNewsBinding, NewsState, NewsViewModel>() {

    override val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var router: Router

    override fun render(state: NewsState) {
    }
}