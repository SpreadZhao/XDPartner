package com.spread.xdplib.adapter.utils

import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.os.Looper


object ProgressDialogUtils {
    private var progressDialog: ProgressDialog? = null //ProgressDialog这个对象你从上面的导入也可以看到，这是Android库自带的
    private var handler = Handler(Looper.getMainLooper())
    private var runnable = {
        hideProgressDialog()
    }
    fun showProgressDialog(context: Context?, message: String?) {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
        handler.postDelayed(runnable,3000)
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
        handler.removeCallbacks(runnable)
    }
}