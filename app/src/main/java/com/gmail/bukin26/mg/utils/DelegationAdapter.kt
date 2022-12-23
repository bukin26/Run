package com.gmail.bukin26.mg.utils

import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gmail.bukin26.mg.databinding.ItemLoadingBinding
import java.lang.reflect.ParameterizedType

class DelegationAdapter<D>(
    private val lifecycleOwner: LifecycleOwner?,
    private val typeToAdapterMap: SparseArray<AdapterDelegate<out D, out ViewBinding>>,
    diffCallbackMap: Map<Class<*>, DiffUtil.ItemCallback<Any>>
) : ListAdapter<D, RecyclerView.ViewHolder>(GenericDiffCallback(diffCallbackMap)),
    LifecycleObserver {

    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LOADING_VIEW_TYPE) {
            LoadingViewHolder(
                binding = ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                context = parent.context,
                lifecycleOwner = lifecycleOwner
            )
        } else {
            typeToAdapterMap[viewType].onCreateViewHolder(parent, viewType, lifecycleOwner)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is LoadingViewHolder) {
            typeToAdapterMap[holder.itemViewType].bindInternal(
                position,
                currentList,
                holder,
                emptyList()
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (holder !is LoadingViewHolder) {
            typeToAdapterMap[holder.itemViewType].bindInternal(
                position,
                currentList,
                holder,
                payloads
            )
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder !is LoadingViewHolder) {
            typeToAdapterMap[holder.itemViewType].onRecycledInternal(holder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isLoadingPosition(position)) {
            return LOADING_VIEW_TYPE
        } else {
            val item = currentList[position]
            for (i in FIRST_VIEW_TYPE until typeToAdapterMap.size()) {
                val delegate = typeToAdapterMap.valueAt(i)
                if (delegate.isForViewType(item)) {
                    return typeToAdapterMap.keyAt(i)
                }
            }
        }

        throw NullPointerException(
            "Can not get viewType for position $position"
        )
    }

    override fun getItemCount(): Int {
        return if (isLoading) {
            currentList.size + 1
        } else {
            super.getItemCount()
        }
    }

    fun submitList(list: List<D>, isLoading: Boolean) {
        if (this.isLoading && !isLoading) {
            this.isLoading = false
            notifyItemRemoved(itemCount)
        }
        super.submitList(list) {
            Handler(Looper.getMainLooper()).post {
                if (list.isNotEmpty() && !this.isLoading && isLoading) {
                    this.isLoading = true
                    notifyItemInserted(itemCount - 1)
                }
            }
        }
    }

    private fun isLoadingPosition(position: Int): Boolean {
        return isLoading && position == itemCount - 1
    }

    class Builder<D> {

        private var count = 0
        private val typeToAdapterMap: SparseArray<AdapterDelegate<out D, out ViewBinding>> =
            SparseArray()
        private val diffCallbackMap: MutableMap<Class<*>, DiffUtil.ItemCallback<Any>> =
            mutableMapOf()

        @Suppress("UNCHECKED_CAST")
        fun <VB : ViewBinding> add(
            delegate: AdapterDelegate<out D, out VB>,
            diffCallback: DiffUtil.ItemCallback<out D>?
        ): Builder<D> {
            typeToAdapterMap.put(count++, delegate)
            diffCallback?.also {
                diffCallbackMap[getDataClass(diffCallback.javaClass)] =
                    diffCallback as DiffUtil.ItemCallback<Any>
            }
            return this
        }

        fun build(lifecycleOwner: LifecycleOwner?): DelegationAdapter<D> {
            return DelegationAdapter(lifecycleOwner, typeToAdapterMap, diffCallbackMap)
        }

        @Suppress("UNCHECKED_CAST")
        private fun getDataClass(clazz: Class<DiffUtil.ItemCallback<out D>>): Class<D> {
            return ((clazz.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<D>)
        }
    }

    companion object {
        private const val FIRST_VIEW_TYPE = 0
        private const val LOADING_VIEW_TYPE = -1
    }
}
