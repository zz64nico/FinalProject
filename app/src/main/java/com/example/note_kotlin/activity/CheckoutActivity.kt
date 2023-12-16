package com.example.note_kotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_kotlin.App
import com.example.note_kotlin.R
import com.example.note_kotlin.adapter.FoodAdapter
import com.example.note_kotlin.adapter.IFoodListener
import com.example.note_kotlin.databinding.ActivityCheckoutBinding
import com.example.note_kotlin.entity.Food
import com.example.note_kotlin.entity.Hotel
import com.example.note_kotlin.entity.Order
import com.example.note_kotlin.widget.MyItemDecoration

class CheckoutActivity : AppCompatActivity()  , IFoodListener {

    lateinit var hotel: Hotel
    var position:Int = -1
    lateinit var foodAdapter: FoodAdapter
    var  list = App.getFoodListNum()
    lateinit var binding:ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkout)
        hotel = intent.getSerializableExtra("hotel") as Hotel;
        binding.tvAddress.text = hotel.address
        foodAdapter = FoodAdapter(this@CheckoutActivity,this)
        binding.rlFood.layoutManager = LinearLayoutManager(this@CheckoutActivity)
        binding.rlFood.addItemDecoration(MyItemDecoration(10))
        binding.rlFood.adapter = foodAdapter;
        foodAdapter.setNewData(App.foodlist)
        list = App.foodlist
        App.foodlistOrder = list
        foodAdapter.notifyDataSetChanged()

        binding.tvPlace.setOnClickListener {
            val des = binding.etSpecial.text.toString()
            var intent = Intent(this@CheckoutActivity,OrderActivity::class.java)
            intent.putExtra("des",des)
            intent.putExtra("hotel",hotel)
            startActivity(intent)
        }
    }


    override fun add(food: Food) {
        for (i in 0..App.getFoodListNum().size){
            if (food.picUrl.equals(App.getFoodList()[i].picUrl)){
                var num:Int = list[i].num+1
                list[i].num = num
                App.foodlistOrder.clear()
                App.foodlistOrder = list
                foodAdapter.setNewData(list)
                foodAdapter.notifyDataSetChanged()
                return
            }
        }
    }

    override fun jian(food: Food) {
        for (i in 0..App.getFoodListNum().size){
            if (food.picUrl.equals(App.getFoodList()[i].picUrl)){
                if (list[i].num <=1){
                    list[i].num =1
                }else{
                    list[i].num-=1
                }
                App.foodlistOrder.clear()
                App.foodlistOrder = list
                foodAdapter.setNewData(list)
                foodAdapter.notifyDataSetChanged()
                return
            }
        }
    }

}