package com.spread.xdplib.adapter.utils

import android.util.Log

object TestLogger {
  private const val TAG = "TestLoggerTag"
  fun logd(msg: String) = Log.d(TAG, msg)
}