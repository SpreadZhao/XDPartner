package com.spread.xdpartner.test

import android.util.Log

object TestLogger {
  private const val TAG = "TestLoggerTag"
  fun log(msg: String) = Log.d(TAG, msg)
}