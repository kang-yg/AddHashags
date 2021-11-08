package com.tagplus.addhashtags.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.MytagItemBinding
import com.tagplus.addhashtags.model.MyTagItem

class MyTagListAdapter(var clipLiveData: MutableLiveData<MutableList<String>>) : ListAdapter<MyTagItem, MyTagListAdapter.MyTagViewHolder>(diffUtil) {
    val clipDataList: ArrayList<String> = arrayListOf()

    inner class MyTagViewHolder(private val itemBinding: MytagItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(myTagItem: MyTagItem) {
            itemBinding.myTagItemTitle.text = myTagItem.item_title
            attachChips(myTagItem.item_tags)
        }

        private fun attachChips(tags: String) {
            itemBinding.myTagItemChipGroup.removeAllViews()

            val splitTags = tags.split(" ")
            splitTags.forEach { tag ->
                if (tag != "#") {
                    itemBinding.myTagItemChipGroup.addView(createChips(tag))
                }
            }
        }

        private fun createChips(tag: String): Chip {
            return Chip(itemBinding.myTagItemChipGroup.context).also { chip ->
                chip.id = View.generateViewId()
                chip.isCheckable = false
                chip.isCheckedIconVisible = false
                chip.text = tag
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose01)
                } else {
                    chip.isCheckedIconVisible = true
                }
/*                chip.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            chip.chipBackgroundColor = itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose05)
                            clipDataList.add(tag)
                            clipLiveData.postValue(clipDataList)
                        }
                    } else {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            chip.chipBackgroundColor = itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose01)
                            clipDataList.remove(tag)
                            clipLiveData.postValue(clipDataList)
                        }
                    }
                }*/
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