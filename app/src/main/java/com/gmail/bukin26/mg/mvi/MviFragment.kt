package com.gmail.bukin26.mg.mvi

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.gmail.bukin26.mg.base.BaseFragment
import com.gmail.bukin26.mg.mvi_core.MviView
import com.gmail.bukin26.mg.mvi_core.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

abstract class MviFragment<VB : ViewBinding, State : UiState, ViewModel : MviViewModel<*, State, *>> :
    BaseFragment<VB>(), MviView<State> {

    protected abstract val viewModel: ViewModel

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchAndRepeatWithViewLifecycle {
            viewModel.stateFlow
                .onEach(::render)
                .collect()
        }
    }
}