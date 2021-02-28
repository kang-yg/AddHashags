package com.tagplus.addhashtags.view

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentLineBreak : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val myClipboard: ClipboardManager = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

//            val clip: ClipData = ClipData.newPlainText("text", "")
//            myClipboard.setPrimaryClip(clip)
            Toast.makeText(activity, "복사완료", Toast.LENGTH_SHORT).show()

        return super.onCreateView(inflater, container, savedInstanceState)
    }


}