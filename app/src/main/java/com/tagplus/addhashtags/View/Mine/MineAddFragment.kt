package com.tagplus.addhashtags.View.Mine

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.View.BaseDialogFragment
import com.tagplus.addhashtags.ViewModel.MineAddFragmentViewModel
import com.tagplus.addhashtags.databinding.FragmentMineAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MineAddFragment : BaseDialogFragment<FragmentMineAddBinding>(FragmentMineAddBinding::inflate) {
    private lateinit var navController: NavController
    private val mineAddFragmentViewModel: MineAddFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        setDialogSizeRatio(0.9f)
        binding?.let {
            initTieAddMineTagText()
            initBtAddMineTagDone()
        }
    }

    private fun initTieAddMineTagText() {
        binding!!.tieAddMineTag.addTextChangedListener(textWatcher())
    }

    private fun initBtAddMineTagDone() {
        binding!!.btAddMineTagDone.setOnClickListener {
            mineAddFragmentViewModel.insertMineHashTagToDB(MineHashTag(title = getTieAddMineTagTitleText(), content = getChipsText(), favorite = false))
            this.dismiss()
        }
    }

    private fun addChip(hashTagText: String) {
        binding?.let {
            val chipGroup = it.cgAddMineTag
            if (chipGroup.childCount < 30) {
                Chip(requireContext()).apply {
                    text = hashTagText
                    isCloseIconVisible = true
                    setOnClickListener { chipView ->
                        chipGroup.removeView(chipView)
                    }
                }.also { chip ->
                    chipGroup.addView(chip)
                }
            }
        }
    }

    private fun getTieAddMineTagTitleText() = binding!!.tieAddMineTagTitle.text.toString()

    private fun getChipsText() = binding!!.cgAddMineTag.children.map { it as Chip }.map { it.text.toString().trim() }.toList()

    private fun textWatcher() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.isNotEmpty())
                if (s.last().toString() == " ") {
                    if (s.toString() != " ") addChip("#".plus(s))
                    binding!!.tieAddMineTag.setText("")
                }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }
}