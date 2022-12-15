package com.tagplus.addhashtags.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.tagplus.addhashtags.Util.dpToPx
import com.tagplus.addhashtags.Util.getDeviceDisplayMetricsPoint

abstract class BaseDialogFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : DialogFragment() {
    private var _binding: VB? = null
    val binding
        get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun setDialogSizeDp(widthDp: Int, heightDp: Int) {
        dialog?.window?.setLayout(requireActivity().dpToPx(widthDp), requireActivity().dpToPx(heightDp))
    }

    protected fun setDialogSizeRatio(widthRatio: Float, heightRatio: Float = 1.0f) {
        val devicePoint = requireActivity().getDeviceDisplayMetricsPoint()
        val params = dialog?.window?.attributes?.apply {
            width = (devicePoint.x * widthRatio).toInt()
            if (heightRatio != 1.0f) height = (devicePoint.y * heightRatio).toInt()
        }
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}