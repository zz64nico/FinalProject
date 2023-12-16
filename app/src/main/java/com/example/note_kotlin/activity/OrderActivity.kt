package com.example.note_kotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_kotlin.App
import com.example.note_kotlin.MainApplication
import com.example.note_kotlin.R
import com.example.note_kotlin.adapter.OrderAdapter
import com.example.note_kotlin.databinding.ActivityOrderBinding
import com.example.note_kotlin.entity.Hotel
import com.example.note_kotlin.entity.Order
import com.example.note_kotlin.widget.MyItemDecoration

class OrderActivity:AppCompatActivity() {
    lateinit var orderAdapter: OrderAdapter
    lateinit var binding:ActivityOrderBinding

    lateinit var hotel: Hotel
    lateinit var des:String
     var list :MutableList<Order> = ArrayList<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_order)
        orderAdapter = OrderAdapter();

        des = intent.getStringExtra("des")!!
        hotel = intent.getSerializableExtra("hotel") as Hotel;
        binding.rlOrder.layoutManager = LinearLayoutManager(this@OrderActivity)
        binding.rlOrder.addItemDecoration(MyItemDecoration(10))
        binding.rlOrder.adapter = orderAdapter;
        var id = System.currentTimeMillis();
        for (i in 0..4){
            id+=1;
            var order = Order()
            order.id = id.toString()
            order.address = hotel.address
            order.detail = hotel.detail
            order.title = App.foodlistOrder[i].name
            order.num = App.foodlistOrder[i].num
            order.tebie = des
            order.time = App.getDate("dd/MM/yyyy")
            order.time_p = App.getDate("HH:mm:ss")
            order.picUrl = hotel.picUrl
            order.money = (App.foodlistOrder[i].num * App.foodlistOrder[i].price.toInt()).toString()
            order.price = (App.foodlistOrder[i].num * App.foodlistOrder[i].price.toInt()).toString()
            MainApplication.instance?.dbManager?.save(order)
            list.add(order)
        }
        orderAdapter.setNewData(list)
        orderAdapter.notifyDataSetChanged()

        binding.tvContinue.setOnClickListener {
            startActivity(Intent(this@OrderActivity,TrackOrderActivity::class.java))
            finish()
        }
    }
}