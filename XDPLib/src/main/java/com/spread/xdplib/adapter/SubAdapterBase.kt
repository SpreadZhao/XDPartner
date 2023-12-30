package com.spread.xdplib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class SubAdapterBase {
  abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewHolder
  abstract fun onBindViewHolder(holder: ViewHolder, data: MultiTypeData, position: Int)
}