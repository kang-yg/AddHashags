package com.tagplus.addhashtags.View.Mine

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.tagplus.addhashtags.View.BaseDialogFragment
import com.tagplus.addhashtags.databinding.FragmentMineAddBinding

class MineAddFragment : BaseDialogFragment<FragmentMineAddBinding>(FragmentMineAddBinding::inflate) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        binding?.let {
            setDialogSizeRatio(0.9f)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}