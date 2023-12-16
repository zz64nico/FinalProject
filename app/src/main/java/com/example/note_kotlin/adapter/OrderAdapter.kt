package com.example.note_kotlin.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.note_kotlin.App
import com.example.note_kotlin.R
import com.example.note_kotlin.entity.Food
import com.example.note_kotlin.entity.Order

class OrderAdapter: BaseQuickAdapter<Order, BaseViewHolder>(R.layout.item_order) {
    override fun convert(holder: BaseViewHolder, item: Order) {
        holder.setText(R.id.tv_name,item.title)
        holder.setText(R.id.tv_des,item.tebie)
        holder.setText(R.id.tv_price,"Price$ :"+(item.num.toInt() * item.price!!.toInt()).toString())
        holder.setText(R.id.tv_quantity,"Quantity:"+item.num.toString())
        holder.setText(R.id.tv_address,"Address"+item.address)
        holder.setText(R.id.tv_date,"Date:"+item.time)
        holder.setText(R.id.tv_time,"Time:"+item.time_p)

    }
}