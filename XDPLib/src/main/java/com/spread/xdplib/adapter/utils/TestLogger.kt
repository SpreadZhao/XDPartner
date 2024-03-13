package com.spread.xdplib.adapter.utils

import android.util.Log

object TestLogger {
  private const val TAG = "TestLoggerTag"
  fun log(msg: String) = Log.d(TAG, msg)
}