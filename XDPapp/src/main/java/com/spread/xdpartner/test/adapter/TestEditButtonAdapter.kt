package com.spread.xdpartner.test.adapter

import android.text.Editable
import android.text.TextWatcher
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
            var str = ""
            holder.button.setOnClickListener {
              value.btnListener.invoke(str)
            }
            holder.button.text = value.btnStr
            holder.editText.addTextChangedListener(object :TextWatcher{
              override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
              }

              override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                  str = s.toString()
              }

              override fun afterTextChanged(s: Editable?) {

              }

            })
        }
    }

    inner class EditButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editText = itemView.findViewById<EditText>(R.id.item_edit_btn_edit)
        val button = itemView.findViewById<Button>(R.id.item_edit_btn_btn)
    }

    class EditButtonData(val btnStr: String, val btnListener: ((msg: String) -> Unit))
}