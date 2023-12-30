package com.spread.xdpartner.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.xdpartner.R
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.SubAdapterBase

class TestEditButtonAdapter : SubAdapterBase() {
  override fun onCreateViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder {
    val itemView = inflater.inflate(R.layout.item_edit_button, parent, false)
    return EditButtonViewHolder(itemView)
  }

  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    data: MultiTypeData,
    position: Int
  ) {
    if (holder is EditButtonViewHolder && data.value is EditButtonData) {
      val value = data.value as EditButtonData
      holder.button.setOnClickListener(value.btnListener)
      holder.button.text = value.btnStr
    }
  }

  inner class EditButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val editText = itemView.findViewById<EditText>(R.id.item_edit_btn_edit)
    val button = itemView.findViewById<Button>(R.id.item_edit_btn_btn)
  }

  class EditButtonData(val btnStr: String, val btnListener: OnClickListener)
}