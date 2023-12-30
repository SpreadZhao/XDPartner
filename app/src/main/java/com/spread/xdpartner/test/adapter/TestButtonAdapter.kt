package com.spread.xdpartner.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.xdpartner.R
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.SubAdapterBase

class TestButtonAdapter : SubAdapterBase() {
  override fun onCreateViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder {
    val itemView = inflater.inflate(R.layout.item_button, parent, false)
    return ButtonViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ViewHolder, data: MultiTypeData, position: Int) {
    if (data.value is ButtonData && holder is ButtonViewHolder) {
      val value = data.value as ButtonData
      holder.button.setOnClickListener(value.listener)
      holder.button.text = value.text
    }
  }

  inner class ButtonViewHolder(itemView: View) : ViewHolder(itemView) {
    val button = itemView.findViewById<Button>(R.id.item_button_btn)
  }

  class ButtonData(val text: String, val listener: OnClickListener)
}