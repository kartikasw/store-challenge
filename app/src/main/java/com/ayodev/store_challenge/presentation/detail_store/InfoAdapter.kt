package com.ayodev.store_challenge.presentation.detail_store

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ayodev.store_challenge.core.domain.model.StoreInfo
import com.ayodev.store_challenge.databinding.ItemStoreInfoBinding
import com.ayodev.store_challenge.util.getCurrentTime
import com.ayodev.store_challenge.util.monthAndYearFormat

class InfoAdapter: RecyclerView.Adapter<InfoAdapter.MyViewHolder>() {

    private var itemList = ArrayList<StoreInfo>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(categories: ArrayList<StoreInfo>) {
        itemList.apply {
            clear()
            addAll(categories)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemStoreInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class MyViewHolder(private val binding: ItemStoreInfoBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(storeInfo: StoreInfo) {
            with(binding) {
                smcMenuTitle.text = storeInfo.title
                smcPercentage.text = "${storeInfo.percentage}%"
                smcDate.text = monthAndYearFormat.format(getCurrentTime())
                smcTarget.text = storeInfo.target.toString()
                smcAchievement.text = storeInfo.achievement.toString()
                layout.background = ContextCompat.getDrawable(itemView.context, storeInfo.color)
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}