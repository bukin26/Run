package com.gmail.bukin26.mg.ui.activity

import androidx.fragment.app.viewModels
import com.gmail.bukin26.mg.databinding.FragmentActivityBinding
import com.gmail.bukin26.mg.mvi.MviFragment

class ActivityFragment : MviFragment<FragmentActivityBinding, ActivityState, ActivityViewModel>() {

    override val viewModel: ActivityViewModel by viewModels()

    override fun render(state: ActivityState) {
    }
}
