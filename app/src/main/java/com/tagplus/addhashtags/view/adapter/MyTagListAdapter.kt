package com.tagplus.addhashtags.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.MytagItemBinding
import com.tagplus.addhashtags.model.MyTagItem

class MyTagListAdapter : ListAdapter<MyTagItem, MyTagListAdapter.MyTagViewHolder>(diffUtil) {
    inner class MyTagViewHolder(val itemBinding: MytagItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(myTagItem: MyTagItem) {
            itemBinding.myTagItemTitle.text = myTagItem.item_title
            splitTags(myTagItem.item_tags)
        }

        private fun splitTags(tags: String) {
            val splitTags = tags.split(" ")
            itemBinding.myTagItemChipGroup.removeAllViews()
            splitTags.forEach { tag ->
                itemBinding.myTagItemChipGroup.addView(createChips(tag))
            }
        }

        private fun createChips(tag: String): Chip {
            return Chip(itemBinding.myTagItemChipGroup.context).also { chip ->
                chip.id = View.generateViewId()
                chip.isCheckable = true
                chip.isCheckedIconVisible = false
                chip.text = tag
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose01)
                } else {
                    chip.isCheckedIconVisible = true
                }
                chip.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            chip.chipBackgroundColor = itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose05)
                        }
                    } else {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            chip.chipBackgroundColor = itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose01)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTagViewHolder {
        return MyTagViewHolder(MytagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyTagViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyTagItem>() {
            override fun areItemsTheSame(oldItem: MyTagItem, newItem: MyTagItem) =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: MyTagItem, newItem: MyTagItem) =
                    oldItem.item_title == newItem.item_title
        }
    }
}