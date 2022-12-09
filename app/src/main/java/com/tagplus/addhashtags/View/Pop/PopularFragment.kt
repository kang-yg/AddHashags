package com.tagplus.addhashtags.View.Pop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.tagplus.addhashtags.View.BaseFragment
import com.tagplus.addhashtags.ViewModel.PopularFragmentViewModel
import com.tagplus.addhashtags.databinding.FragmentPopBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopBinding>(FragmentPopBinding::inflate) {
    private val popularFragmentViewModel: PopularFragmentViewModel by viewModels()
    private val popListAdapter = PopListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            initRvPop()
            observePopularHashTagsLiveData(viewLifecycleOwner)
        }
    }

    private fun initRvPop() {
        binding!!.rvPop.adapter = popListAdapter
    }

    private fun observePopularHashTagsLiveData(lifecycleOwner: LifecycleOwner) {
        popularFragmentViewModel.popularHashTagsLiveData.observe(lifecycleOwner) {
            popListAdapter.submitList(it)
        }
    }
}