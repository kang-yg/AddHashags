package com.tagplus.addhashtags.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentMineAddtagBinding
import com.tagplus.addhashtags.databinding.FragmentMineBinding
import com.tagplus.addhashtags.view.viewmodelfactory.FragmentMineAddTagViewModelFactory
import com.tagplus.addhashtags.viewmodel.FragmentMineAddTagViewModel

class FragmentMineAddTag : Fragment() {
    private lateinit var fragmentMineBinding: FragmentMineBinding
    private lateinit var fragmentMineAddTagBinding: FragmentMineAddtagBinding
    private lateinit var fragmentMineAddTagViewModel: FragmentMineAddTagViewModel
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(requireContext(), AppDatabase::class.java, "AddHashTags").build()
    }
    private val fragmentManager by lazy {
        activity?.supportFragmentManager?.beginTransaction()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)
        fragmentMineAddTagBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_addtag, container, false)
        fragmentMineAddTagViewModel = ViewModelProvider(this, FragmentMineAddTagViewModelFactory(db)).get(FragmentMineAddTagViewModel::class.java)

        return fragmentMineAddTagBinding.root
    }

    override fun onStart() {
        super.onStart()
        initDataToLayout()
        initFragmentMineAddTagConstraintLayout()
    }

    private fun initDataToLayout() {
        fragmentMineAddTagBinding.fragmentAddTag = this
    }

    private fun initFragmentMineAddTagConstraintLayout() {
        // ConstraintLayout 선택 시 포커스 제거 및 키도드 닫힘
        fragmentMineAddTagBinding.addTagsConstraint.setOnClickListener {
            it.clearFocus()
            val inputManager = it.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun submit() {
        val title = fragmentMineAddTagBinding.tagContentTitleEdit.text.toString()
        val content = fragmentMineAddTagBinding.tagContentEdit.text.toString()
        Thread(Runnable {
            fragmentMineAddTagViewModel.addTagData(title, content).run {
                activity?.runOnUiThread {
                    fragmentManager?.replace(fragmentMineBinding.mineFrame.id, FragmentMineTagList())?.commit()
                }
            }
        }).start()
    }

    fun back() {
        activity?.supportFragmentManager?.popBackStack()
    }
}