package com.gmail.bukin26.mg.ui.activity

import androidx.fragment.app.viewModels
import com.gmail.bukin26.mg.databinding.FragmentActivityBinding
import com.gmail.bukin26.mg.mvi.MviFragment
import com.gmail.bukin26.mg.navigation.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivityFragment : MviFragment<FragmentActivityBinding, ActivityState, ActivityViewModel>() {

    override val viewModel: ActivityViewModel by viewModels()

    @Inject
    lateinit var router: Router

    override fun render(state: ActivityState) {
    }
}
