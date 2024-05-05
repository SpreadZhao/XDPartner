package com.spread.xdplib.adapter.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun closeSoftInput(context:Context,v: View?) {
    if (v != null) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0);
    }

}