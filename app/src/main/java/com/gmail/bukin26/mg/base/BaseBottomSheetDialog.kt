package com.gmail.bukin26.mg.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

abstract class BaseBottomSheetDialog<VB : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createViewBindingInstance(
            clazz = getViewBindingFromGeneric(),
            inflater = inflater,
            container = container,
            attachToRoot = false
        )
        return binding.root
    }

    @Suppress("SameParameterValue", "UNCHECKED_CAST")
    private fun createViewBindingInstance(
        clazz: Class<VB>,
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): VB {
        return clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(clazz, inflater, container, attachToRoot) as VB
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewBindingFromGeneric(): Class<VB> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
    }

    inline fun Fragment.launchAndRepeatWithViewLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
                block()
            }
        }
    }
}