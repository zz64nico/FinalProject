package com.example.note_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.note_kotlin.R
import com.example.note_kotlin.adapter.ImageAdapter.BannerViewHolder
import com.youth.banner.adapter.BannerAdapter

/**
 * @description:banner显示
 */
class ImageAdapter(mDatas: List<String?>?) : BannerAdapter<String?, BannerViewHolder>(mDatas) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        val lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        view.layoutParams = lp
        return BannerViewHolder(view)
    }


    override fun onBindView(holder: BannerViewHolder?, data: String?, position: Int, size: Int) {
        Glide.with(holder!!.itemView)
            .load(data)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(holder.imageView)
    }

    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var textView: TextView? = null

        init {
            imageView = view.findViewById<View>(R.id.imageview) as ImageView
        }
    }
}