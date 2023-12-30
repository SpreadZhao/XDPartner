package com.spread.xdplib.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MultiTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  companion object {
    private const val VIEW_TYPE_NO_TYPE = -1
  }

  private var mDataSet: List<MultiTypeData>? = null

  private val mSubAdapterMapViaType = SparseArray<SubAdapterBase>()

  private var mInflater: LayoutInflater? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val subAdapter = mSubAdapterMapViaType[viewType]
    if (mInflater == null) {
      mInflater = LayoutInflater.from(parent.context)
    }
    return subAdapter.onCreateViewHolder(mInflater!!, parent, viewType)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (mDataSet != null && position in mDataSet!!.indices) {
      val type = mDataSet!![position].type
      val subAdapter = mSubAdapterMapViaType[type]
      subAdapter.onBindViewHolder(holder, mDataSet!![position], position)
    }
  }

  override fun getItemCount() = mDataSet?.size ?: 0

  override fun getItemViewType(position: Int): Int {
    if (mDataSet == null) {
      return VIEW_TYPE_NO_TYPE
    }
    if (position !in mDataSet!!.indices) {
      return VIEW_TYPE_NO_TYPE
    }
    return mDataSet!![position].type
  }

  fun configDataSet(dataSet: List<MultiTypeData>) {
    mDataSet = dataSet
  }

  fun addSubAdapter(type: Int, adapter: SubAdapterBase) {
    mSubAdapterMapViaType[type] = adapter
  }
}

data class MultiTypeData(val type: Int, val value: Any?)