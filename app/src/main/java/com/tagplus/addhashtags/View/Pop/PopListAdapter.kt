package com.tagplus.addhashtags.View.Pop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tagplus.addhashtags.Model.HashTag
import com.tagplus.addhashtags.databinding.ViewRvPopItemBinding

class PopListAdapter : ListAdapter<HashTag, PopListAdapter.PopListAdapterViewHolder>(diff) {
    class PopListAdapterViewHolder(private val viewRvPopItemBinding: ViewRvPopItemBinding) : RecyclerView.ViewHolder(viewRvPopItemBinding.root) {
        fun bind(position: Int, currentHashTah: HashTag) {
            with(viewRvPopItemBinding) {
                tvRvPopItemNo.text = (position.plus(1)).toString()
                tvRvPopItemContent.text = currentHashTah.content
                tvRvPopItemContentCount.text = currentHashTah.count.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopListAdapterViewHolder {
        val binding = ViewRvPopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopListAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopListAdapterViewHolder, position: Int) {
        holder.bind(position, currentList[position])
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<HashTag>() {
            override fun areItemsTheSame(oldItem: HashTag, newItem: HashTag): Boolean = oldItem.content == newItem.content

            override fun areContentsTheSame(oldItem: HashTag, newItem: HashTag): Boolean = oldItem == newItem
        }
    }
}