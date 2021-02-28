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
import com.tagplus.addhashtags.view.viewmodelfactory.FragmentMineAddTagViewModelFactory
import com.tagplus.addhashtags.viewmodel.FragmentMineAddTagViewModel

class FragmentMineAddTag : Fragment() {
    private lateinit var fragmentMineAddTagBinding: FragmentMineAddtagBinding
    private lateinit var fragmentMineAddTagViewModel: FragmentMineAddTagViewModel

    private lateinit var db: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "AddHashTags").build()
        fragmentMineAddTagBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_addtag, container, false)
        fragmentMineAddTagViewModel = ViewModelProvider(this, FragmentMineAddTagViewModelFactory(db)).get(FragmentMineAddTagViewModel::class.java)
        fragmentMineAddTagBinding.fragmentAddTag = this

        fragmentMineAddTagBinding.addTagsConstraint.setOnClickListener {
            it.clearFocus()
            val inputManager = it.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(it.windowToken, 0)
        }

        return fragmentMineAddTagBinding.root
    }

    // TODO ReactiveX 이용 더블클릭 방지
    fun submit() {
        val title = fragmentMineAddTagBinding.tagContentTitleEdit.text.toString()
        val content = fragmentMineAddTagBinding.tagContentEdit.text.toString()
        fragmentMineAddTagViewModel.addTagData(title, content)

        activity?.supportFragmentManager?.popBackStack()
    }

    fun back() {
        activity?.supportFragmentManager?.popBackStack()
    }
}