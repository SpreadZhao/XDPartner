package com.spread.XDPBusiness.XDPAdd.adapter

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdpBusiness.xdpaddnote.databinding.ItemEditBinding
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.SubAdapterBase

class EditTextAdapter :SubAdapterBase() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflate = ItemEditBinding.inflate(inflater,parent,false)
        return EditViewHolder(inflate.root)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        data: MultiTypeData,
        position: Int
    ) {
        if (holder is EditViewHolder && data.value is EditData) {
            val value = data.value as EditData
            holder.editText.addTextChangedListener(value.textWatcher)
            holder.editText.hint = value.hint
            if(value.choose){
                holder.textView.text = "必填"
            } else{
                holder.textView.text = "选填"
            }
        }
    }
    inner class EditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemEditBinding.bind(itemView)
        val editText = binding.itemEditText
        val textView = binding.itemChoose
    }
    class EditData(val btnStr: String,val choose :Boolean,val hint:String, val textWatcher: TextWatcher)
}