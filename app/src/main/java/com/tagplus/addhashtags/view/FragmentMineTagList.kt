package com.tagplus.addhashtags.view

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.Common
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentMineTaglistBinding
import com.tagplus.addhashtags.model.MyTagItem
import com.tagplus.addhashtags.view.adapter.MyTagListAdapter
import com.tagplus.addhashtags.view.viewmodelfactory.FragmentMineTagListViewModelFactory
import com.tagplus.addhashtags.viewmodel.FragmentMineTagListViewModel

class FragmentMineTagList : Fragment() {
    private lateinit var fragmentMineTagListBinding: FragmentMineTaglistBinding
    private lateinit var fragmentMineTagListViewModel: FragmentMineTagListViewModel

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(requireContext(), AppDatabase::class.java, "AddHashTags").build()
    }
    private val mineTagListRecyclerView: RecyclerView by lazy {
        fragmentMineTagListBinding.mineRecyclerView
    }
    private val myTagListAdapter: MyTagListAdapter by lazy {
        MyTagListAdapter(fragmentMineTagListViewModel.getClipDataList(), copyEvent = copyButtonClick(), removeEvent = removeButtonClick())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMineTagListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_taglist, container, false)
        fragmentMineTagListViewModel = ViewModelProvider(this, FragmentMineTagListViewModelFactory(db)).get(FragmentMineTagListViewModel::class.java)

        return fragmentMineTagListBinding.root
    }

    override fun onStart() {
        super.onStart()
        initDataToLayout()
        setRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        showTagList()
    }

    private fun initDataToLayout() {
        fragmentMineTagListBinding.fragmentMineTagList = this
    }

    // TODO RecyclerView.ItemDecoration 적용
    private fun setRecyclerView() {
        mineTagListRecyclerView.layoutManager = LinearLayoutManager(context)
        mineTagListRecyclerView.adapter = myTagListAdapter
    }

    private fun showTagList() {
        Thread(Runnable {
            fragmentMineTagListViewModel.getAllMyTagItems().run {
                activity?.runOnUiThread {
                    myTagListAdapter.submitList(this.reversed())
                }
            }
        }).start()
    }

    fun floatingButtonClick() {
        val mineTagListFrame = fragmentMineTagListBinding.mineTagListFrame
        val fragmentManager = requireFragmentManager().beginTransaction()
        fragmentManager.replace(mineTagListFrame.id, FragmentMineAddTag()).commitAllowingStateLoss()
        fragmentManager.addToBackStack(null)
    }

    private fun copyButtonClick(): () -> Unit = {
        fragmentMineTagListViewModel.copyHashTags(activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        Common.showToast(requireContext(), getString(R.string.copied_success))
    }

    private fun removeButtonClick(): (MyTagItem) -> Unit = {
        Thread(Runnable {
            fragmentMineTagListViewModel.deleteTagData(it).run {
                showTagList()
            }
        }).start()
    }
}