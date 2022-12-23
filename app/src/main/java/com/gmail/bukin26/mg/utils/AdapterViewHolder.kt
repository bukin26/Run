package com.gmail.bukin26.mg.utils

import android.content.Context
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class AdapterViewHolder<D, VB : ViewBinding>(
    val binding: VB,
    val context: Context,
    lifecycleOwner: LifecycleOwner?
) : RecyclerView.ViewHolder(binding.root) {

    private var _item: D? = null
    val item: D
        get() = _item!!

    private var onBind: (() -> Unit)? = null
    private var onApplyChanges: ((Set<Any>) -> Unit)? = null
    private var onRecycled: (() -> Unit)? = null
    private var onViewAttached: (() -> Unit)? = null
    private var onViewDetached: (() -> Unit)? = null
    private var onResume: (() -> Unit)? = null
    private var onPause: (() -> Unit)? = null

    private val onAttachStateChangeListener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {
            onViewAttached?.invoke()
        }

        override fun onViewDetachedFromWindow(v: View) {
            onViewDetached?.invoke()
        }
    }

    init {
        lifecycleOwner?.lifecycle?.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResume() {
                if (itemView.isAttachedToWindow) {
                    onResume?.invoke()
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPause() {
                if (itemView.isAttachedToWindow) {
                    onPause?.invoke()
                }
            }
        })
    }

    fun onBind(onBind: () -> Unit) {
        this.onBind = onBind
    }

    fun onApplyChanges(onApplyChanges: (Set<Any>) -> Unit) {
        this.onApplyChanges = onApplyChanges
    }

    fun onRecycled(onRecycled: () -> Unit) {
        this.onRecycled = onRecycled
    }

    fun onViewAttached(onViewAttached: () -> Unit) {
        this.onViewAttached = onViewAttached
    }

    fun onViewDetached(onViewDetached: () -> Unit) {
        this.onViewDetached = onViewDetached
    }

    fun onResume(onResume: () -> Unit) {
        this.onResume = onResume
    }

    fun onPause(onPause: () -> Unit) {
        this.onPause = onPause
    }

    internal fun onBind(item: D, payloads: List<Any>) {
        this._item = item
        binding.root.addOnAttachStateChangeListener(onAttachStateChangeListener)
        val payloadSet = hashSetOf<Any>()
        payloads.forEach { list ->
            (list as? List<*>)?.forEach {
                it?.also { payloadSet.add(it) }
            }
        }
        if (payloadSet.isEmpty()) {
            onBind?.invoke()
        } else {
            onApplyChanges?.invoke(payloadSet)
        }
    }

    internal fun onResume() {
        onResume?.invoke()
    }

    internal fun onPause() {
        onPause?.invoke()
    }

    internal fun onRecycled() {
        binding.root.removeOnAttachStateChangeListener(onAttachStateChangeListener)
        onRecycled?.invoke()
    }
}
