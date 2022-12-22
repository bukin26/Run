package com.gmail.bukin26.mg.ui.run

import androidx.fragment.app.viewModels
import com.gmail.bukin26.mg.databinding.FragmentRunBinding
import com.gmail.bukin26.mg.mvi.MviFragment
import com.gmail.bukin26.mg.navigation.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RunFragment : MviFragment<FragmentRunBinding, RunState, RunViewModel>() {

    override val viewModel: RunViewModel by viewModels()

    @Inject
    lateinit var router: Router

    override fun render(state: RunState) {
    }
}