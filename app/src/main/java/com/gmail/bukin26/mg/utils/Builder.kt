@file:Suppress("FunctionName")
package com.gmail.bukin26.mg.utils

import androidx.lifecycle.LifecycleOwner

fun <D> BuildDelegationAdapter(lifecycleOwner: LifecycleOwner? = null, block: DelegationAdapter.Builder<D>.() -> Unit): DelegationAdapter<D> {
    val builder = DelegationAdapter.Builder<D>()
    block(builder)
    return builder.build(lifecycleOwner)
}
