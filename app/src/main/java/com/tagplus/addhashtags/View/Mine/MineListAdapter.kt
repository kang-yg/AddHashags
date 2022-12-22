package com.tagplus.addhashtags.View.Mine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.ViewRvMineItemBinding

class MineListAdapter(private val callback: (MineRecyclerViewItemEvent, MineHashTag) -> Unit) : ListAdapter<MineHashTag, MineListAdapter.MineListAdapterViewHolder>(diff) {
    inner class MineListAdapterViewHolder(private val viewRvMineItemBinding: ViewRvMineItemBinding) : RecyclerView.ViewHolder(viewRvMineItemBinding.root) {
        fun bind(mineHashTag: MineHashTag) {
            with(viewRvMineItemBinding) {
                cvRvMineItem.setOnClickListener {
                    mineHashTag.expand = !mineHashTag.expand
                    tvMineHashTagContent.isSingleLine = !mineHashTag.expand
                    tvMineHashTagTitle.isSingleLine = !mineHashTag.expand
                }
                tvMineHashTagTitle.text = mineHashTag.title
                tvMineHashTagContent.text = mineHashTag.content.joinToString(" ")
                with(ivMineHashTagContentFavorite) {
                    if (mineHashTag.favorite) setImageResource(R.drawable.ic_baseline_star_24)
                    else setImageResource(R.drawable.ic_baseline_star_outline_24)
                    setOnClickListener(clickCallback(FAVORITE, mineHashTag))
                }
                ivMineHashTagContentCopy.setOnClickListener(clickCallback(COPY, mineHashTag))
                ivMineHashTagContentRemove.setOnClickListener(clickCallback(DELETE, mineHashTag))
            }
        }

        private fun clickCallback(mineRecyclerViewItemEvent: MineRecyclerViewItemEvent, mineHashTag: MineHashTag) =
            View.OnClickListener { callback(mineRecyclerViewItemEvent, mineHashTag) }
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