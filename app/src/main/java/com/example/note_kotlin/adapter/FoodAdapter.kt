package com.example.note_kotlin.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.note_kotlin.R
import com.example.note_kotlin.entity.Food
import com.example.note_kotlin.entity.Hotel

class FoodAdapter (context: Context, var iFoodListener: IFoodListener):
    BaseQuickAdapter<Food, BaseViewHolder>(R.layout.item_food) {
    override fun convert(holder: BaseViewHolder, item: Food) {
        Glide.with(context).load(item.picUrl).into(holder.getView(R.id.img_food));
        holder.setText(R.id.tv_food,item.name)
        holder.setText(R.id.tv_money,(item.num*item.price).toString())
        holder.setText(R.id.tv_num,item.num.toString())
        holder.getView<View>(R.id.img_add).setOnClickListener {
            if (iFoodListener != null) {
                iFoodListener!!.add(item)
            }
        }
        holder.getView<View>(R.id.img_jian).setOnClickListener {
            if (iFoodListener != null) {
                iFoodListener!!.jian(item)
            }
        }
    }

}