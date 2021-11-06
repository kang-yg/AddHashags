package com.tagplus.addhashtags

import android.content.Context
import android.widget.Toast

object Common {
    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}