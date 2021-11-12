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

class MyTagListAdapter(private var clipData: ArrayList<String>, private val copyEvent: () -> Unit, private val removeEvent: (MyTagItem) -> Unit) : ListAdapter<MyTagItem, MyTagListAdapter.MyTagViewHolder>(diffUtil) {
    private val selectItemPosition: ArrayList<Int> = arrayListOf()

    inner class MyTagViewHolder(private val itemBinding: MytagItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(myTagItem: MyTagItem, position: Int) {
            itemBinding.myTagItemTitle.text = myTagItem.item_title
            attachChips(myTagItem.item_tags, position)
            cardView(position)
            myTagItemOptionConstraintLayout(position)
            copyButtonEvent(myTagItem)
            removeButtonEvent(myTagItem)
            setVisibilityMyTagItemOptionConstraintLayout(position)
        }

        private fun setVisibilityMyTagItemOptionConstraintLayout(position: Int) {
            when (selectItemPosition.contains(position)) {
                true -> itemBinding.myTagItemOptionConstraintLayout.visibility = View.VISIBLE
                false -> itemBinding.myTagItemOptionConstraintLayout.visibility = View.GONE

            }
        }

        private fun cardView(position: Int) {
            itemBinding.myTagItemCardView.setOnClickListener {
                selectItemPosition.add(position)
                itemBinding.myTagItemOptionConstraintLayout.visibility = View.VISIBLE
            }
        }

        private fun myTagItemOptionConstraintLayout(position: Int) {
            itemBinding.myTagItemOptionConstraintLayout.setOnClickListener {
                selectItemPosition.remove(position)
                itemBinding.myTagItemOptionConstraintLayout.visibility = View.GONE
            }
        }

        private fun copyButtonEvent(myTagItem: MyTagItem) {
            itemBinding.myTagItemCopyBt.setOnClickListener {
                clipData.clear()
                val splitTags = myTagItem.item_tags.split(" ")
                splitTags.forEach { tag ->
                    if (tag != "#") {
                        clipData.add(tag)
                    }
                }
                copyEvent()
            }
        }

        private fun removeButtonEvent(myTagItem: MyTagItem) {
            itemBinding.myTagItemRemoveBt.setOnClickListener {
                removeEvent(myTagItem)
            }
        }

        private fun attachChips(tags: String, position: Int) {
            itemBinding.myTagItemChipGroup.removeAllViews()

            val splitTags = tags.split(" ")
            splitTags.forEach { tag ->
                if (tag != "#") {
                    itemBinding.myTagItemChipGroup.addView(createChips(tag, position))
                }
            }
        }

        private fun createChips(tag: String, position: Int): Chip {
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
                chip.setOnClickListener {
                    selectItemPosition.add(position)
                    itemBinding.myTagItemOptionConstraintLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTagViewHolder {
        return MyTagViewHolder(MytagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyTagViewHolder, position: Int) {
        holder.bind(currentList[position], position)
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