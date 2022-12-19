package com.tagplus.addhashtags.View.Mine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.View.BaseFragment
import com.tagplus.addhashtags.ViewModel.MineFragmentViewModel
import com.tagplus.addhashtags.databinding.FragmentMineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MineFragment : BaseFragment<FragmentMineBinding>(FragmentMineBinding::inflate) {
    private lateinit var navController: NavController
    private val mineFragmentViewModel: MineFragmentViewModel by viewModels()
    private val mineListAdapter = MineListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        binding?.let {
            initRvMine()
            observeAllHashTagsLiveDataFromDB()
            it.btShowFragmentMineAdd.setOnClickListener {
                navController.navigate(R.id.action_mineFragment_to_mineAddFragment)
            }
        }
    }

    private fun initRvMine() {
        binding!!.rvMine.adapter = mineListAdapter
    }

    private fun observeAllHashTagsLiveDataFromDB() {
        mineFragmentViewModel.allHashTagsLiveDataFromDB.observe(viewLifecycleOwner) {
            mineListAdapter.submitList(it)
        }
    }
}