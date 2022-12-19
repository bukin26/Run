package com.gmail.bukin26.mg.ui.run

import androidx.fragment.app.viewModels
import com.gmail.bukin26.mg.databinding.FragmentRunBinding
import com.gmail.bukin26.mg.mvi.MviFragment

class RunFragment : MviFragment<FragmentRunBinding, RunState, RunViewModel>() {

    override val viewModel: RunViewModel by viewModels()

    override fun render(state: RunState) {
        TODO("Not yet implemented")
    }
}