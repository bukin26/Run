package com.gmail.bukin26.mg.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.gmail.bukin26.mg.databinding.ItemLoadingBinding

class LoadingViewHolder(
    binding: ItemLoadingBinding,
    context: Context,
    lifecycleOwner: LifecycleOwner?
) : AdapterViewHolder<Any, ItemLoadingBinding>(binding, context, lifecycleOwner)
