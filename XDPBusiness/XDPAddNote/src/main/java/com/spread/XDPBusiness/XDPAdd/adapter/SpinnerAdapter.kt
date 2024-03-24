package com.spread.XDPBusiness.XDPAdd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.spread.xdpBusiness.xdpaddnote.databinding.ItemSpinnerBinding
import com.spread.xdplib.adapter.MultiTypeData
import com.spread.xdplib.adapter.SubAdapterBase

class SpinnerAdapter :SubAdapterBase() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemView = ItemSpinnerBinding.inflate( inflater,parent,false)
        return SpinnerHolder(itemView.root)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        data: MultiTypeData,
        position: Int
    ) {
        if (holder is SpinnerHolder && data.value is SpinnerData) {
            val value = data.value as SpinnerData
            holder.spinner.onItemSelectedListener = value.listener 
        }
    }
    inner class SpinnerHolder(val itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemSpinnerBinding.bind(itemView)
        val spinner = binding.itemSp
    }
    class SpinnerData(val listener: AdapterView.OnItemSelectedListener)
}