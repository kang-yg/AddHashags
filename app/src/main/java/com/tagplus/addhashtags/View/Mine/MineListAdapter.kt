package com.tagplus.addhashtags.View.Mine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.databinding.ViewRvMineItemBinding

class MineListAdapter : ListAdapter<MineHashTag, MineListAdapter.MineListAdapterViewHolder>(diff) {
    class MineListAdapterViewHolder(private val viewRvMineItemBinding: ViewRvMineItemBinding) : RecyclerView.ViewHolder(viewRvMineItemBinding.root) {
        fun bind(mineHashTag: MineHashTag) {
            with(viewRvMineItemBinding) {
                tvMineHashTagTitle.text = mineHashTag.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineListAdapterViewHolder {
        val binding = ViewRvMineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MineListAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MineListAdapterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<MineHashTag>() {
            override fun areItemsTheSame(oldItem: MineHashTag, newItem: MineHashTag): Boolean = oldItem.content.containsAll(newItem.content)

            override fun areContentsTheSame(oldItem: MineHashTag, newItem: MineHashTag): Boolean = oldItem == newItem
        }
    }
}