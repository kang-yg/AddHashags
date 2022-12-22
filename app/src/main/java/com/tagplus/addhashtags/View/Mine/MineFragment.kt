package com.tagplus.addhashtags.View.Mine

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.View.BaseFragment
import com.tagplus.addhashtags.ViewModel.MineFragmentViewModel
import com.tagplus.addhashtags.databinding.FragmentMineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MineFragment : BaseFragment<FragmentMineBinding>(FragmentMineBinding::inflate) {
    private lateinit var navController: NavController
    private val mineFragmentViewModel: MineFragmentViewModel by viewModels()
    private val mineListAdapter = MineListAdapter { mineRecyclerViewItemEvent, mineHashTag ->
        itemEvent(mineRecyclerViewItemEvent, mineHashTag)
    }
    private val mineFavoriteListAdapter = MineListAdapter { mineRecyclerViewItemEvent, mineHashTag ->
        itemEvent(mineRecyclerViewItemEvent, mineHashTag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        binding?.let {
            observeAllHashTagsLiveDataFromDB()
            observerFavoriteHashTagsLiveDataFromDB()
            observeBtShowFragmentMineSwitchAllAndFavoriteStateLiveData()
            it.btShowFragmentMineAdd.setOnClickListener {
                navController.navigate(R.id.action_mineFragment_to_mineAddFragment)
            }
            it.btShowFragmentMineSwitchAllAndFavorite.setOnClickListener { _ ->
                val currentSwitchAllAndFavoriteLiveDataState = mineFragmentViewModel.btShowFragmentMineSwitchAllAndFavoriteStateLiveData.value!!
                mineFragmentViewModel.switchAllAndFavoriteLiveDataState(!currentSwitchAllAndFavoriteLiveDataState)
            }
        }
    }

    private fun observeAllHashTagsLiveDataFromDB() {
        mineFragmentViewModel.allHashTagsLiveDataFromDB.observe(viewLifecycleOwner) {
            mineListAdapter.submitList(it)
        }
    }

    private fun observerFavoriteHashTagsLiveDataFromDB() {
        mineFragmentViewModel.favoriteHashTagsLiveDataFromDB.observe(viewLifecycleOwner) {
            mineFavoriteListAdapter.submitList(it)
        }
    }

    private fun observeBtShowFragmentMineSwitchAllAndFavoriteStateLiveData() {
        mineFragmentViewModel.btShowFragmentMineSwitchAllAndFavoriteStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    binding!!.rvMine.adapter = mineListAdapter
                    with(binding!!.btShowFragmentMineSwitchAllAndFavorite) {
                        text = getString(R.string.all)
                    }
                }

                false -> {
                    binding!!.rvMine.adapter = mineFavoriteListAdapter
                    with(binding!!.btShowFragmentMineSwitchAllAndFavorite) {
                        text = getString(R.string.favorites)
                    }
                }
            }
        }
    }

    private fun itemEvent(mineRecyclerViewItemEvent: MineRecyclerViewItemEvent, mineHashTag: MineHashTag) = when (mineRecyclerViewItemEvent) {
        FAVORITE -> {
            updateMineHashTagToDB(mineHashTag)
        }

        COPY -> {
            copyMineHashTagsToClipBoard(mineHashTag)
        }

        DELETE -> {
            deleteMineHashTagFromDB(mineHashTag)
        }
    }

    private fun updateMineHashTagToDB(mineHashTag: MineHashTag) = mineFragmentViewModel.updateMineHashTagToDB(mineHashTag.copy(favorite = !mineHashTag.favorite))

    private fun copyMineHashTagsToClipBoard(mineHashTag: MineHashTag) {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val tags = mineHashTag.content.joinToString(" ")
        val clip = ClipData.newPlainText("My HashTag", tags)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(requireContext(), getString(R.string.copy_success), Toast.LENGTH_SHORT).show()
    }

    private fun deleteMineHashTagFromDB(mineHashTag: MineHashTag) = mineFragmentViewModel.deleteMineHashTagFromDB(mineHashTag)
}