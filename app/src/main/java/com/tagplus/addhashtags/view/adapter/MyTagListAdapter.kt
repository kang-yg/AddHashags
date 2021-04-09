package com.tagplus.addhashtags.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.MytagItemBinding
import com.tagplus.addhashtags.model.MyTagItem

class MyTagListAdapter : RecyclerView.Adapter<MyTagListAdapter.MyTagViewHolder>() {
    var tagItemList = MutableLiveData<ArrayList<MyTagItem>>()

    class MyTagViewHolder(val itemBinding: MytagItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTagViewHolder {
        val binding = DataBindingUtil.inflate<MytagItemBinding>(LayoutInflater.from(parent.context), R.layout.mytag_item, parent, false)

        return MyTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyTagViewHolder, position: Int) {
        holder.itemBinding.myTagItemChipGroup.removeAllViews()
        tagItemList.apply {
            holder.itemBinding.myTagItem = this.value?.get(position) ?: MyTagItem("Dummy", "NONE")
            val splitItemTags = this.value?.get(position)?.item_tags?.split(" ")
            // TODO 안드로이드 버전에 따른 Chip 설정 변경
            if (splitItemTags != null) {
                for (element in splitItemTags) {
                    val chip = Chip(holder.itemBinding.myTagItemChipGroup.context).also {
                        it.id = View.generateViewId()
                        it.isCheckable = true
                        it.isCheckedIconVisible = false
                        it.text = element
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            it.chipBackgroundColor = holder.itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose01)
                        } else {
                            it.isCheckedIconVisible = true
                        }

                        it.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    it.chipBackgroundColor = holder.itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose05)
                                }
                            } else {
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    it.chipBackgroundColor = holder.itemBinding.myTagItemChipGroup.context.getColorStateList(R.color.MistyRose01)
                                }
                            }
                        }
                    }
                    holder.itemBinding.myTagItemChipGroup.addView(chip)
                }
            }
            holder.itemBinding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return tagItemList.value?.size ?: 0
    }
}