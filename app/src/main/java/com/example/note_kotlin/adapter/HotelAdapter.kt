package com.example.note_kotlin.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.note_kotlin.R
import com.example.note_kotlin.entity.Hotel

class HotelAdapter(context: Context, var iHotelListener: IHotelListener):BaseQuickAdapter<Hotel,BaseViewHolder>(R.layout.item) {
    override fun convert(holder: BaseViewHolder, item: Hotel) {
        Glide.with(context).load(item.picUrl).into(holder.getView(R.id.image));
        holder.setText(R.id.tv_title,item.title)
        holder.setText(R.id.tv_des,item.address)
        holder.getView<View>(R.id.card_view).setOnClickListener {
            if (iHotelListener != null) {
                iHotelListener!!.onHomeClick(item)
            }
        }
    }

}