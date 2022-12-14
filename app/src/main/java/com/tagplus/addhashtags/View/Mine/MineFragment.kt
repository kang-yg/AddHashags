package com.tagplus.addhashtags.View.Mine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.tagplus.addhashtags.View.BaseFragment
import com.tagplus.addhashtags.ViewModel.MineFragmentViewModel
import com.tagplus.addhashtags.databinding.FragmentMineBinding

class MineFragment : BaseFragment<FragmentMineBinding>(FragmentMineBinding::inflate) {
    private val mineFragmentViewModel: MineFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {

        }
    }
}