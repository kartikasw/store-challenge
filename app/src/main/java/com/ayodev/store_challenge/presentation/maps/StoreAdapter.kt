package com.ayodev.store_challenge.presentation.maps

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.databinding.ItemStoreBinding
import com.ayodev.store_challenge.databinding.ItemStoreVisitedBinding
import com.ayodev.store_challenge.util.dateFormat
import java.util.*

class StoreAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val NOT_VISITED = 0
        const val VISITED = 1
    }

    private var itemList = ArrayList<Store>()

    var onItemClick: ((Store) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Store>) {
        itemList.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            NOT_VISITED -> {
                val view =
                    ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NotVisitedViewHolder(view)
            }
            VISITED -> {
                val view =
                    ItemStoreVisitedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VisitedViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NotVisitedViewHolder -> {
                holder.bind(itemList[position])
            }
            is VisitedViewHolder -> {
                holder.bind(itemList[position])
            }
        }
    }

    inner class NotVisitedViewHolder(private val binding: ItemStoreBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(itemList[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(store: Store) {
            with(binding) {
                isTvName.text = store.store_name
                isTvDistance.text = "${String.format("% .1f", store.distance)} km"
            }
        }
    }

    inner class VisitedViewHolder(
        private val binding: ItemStoreVisitedBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(itemList[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(store: Store) {
            with(binding) {
                isvTvName.text = store.store_name
                isvTvDistance.text = "${String.format("% .1f", store.distance)} km"

            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int =
        when(itemList[position].visit) {
            false -> NOT_VISITED
            true -> {
                val visit = itemList[position].visit_date
                val visitDate = dateFormat.format(visit?.time)

                val time = Calendar.getInstance().time
                val date = dateFormat.format(time)

                when(visitDate == date) {
                    true ->  VISITED
                    false -> NOT_VISITED
                }
            }
        }

}