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
import com.example.note_kotlin.adapter.ImageAdapter
import com.example.note_kotlin.databinding.ActivityRestaurantBinding
import com.example.note_kotlin.entity.Food
import com.example.note_kotlin.entity.Hotel
import com.example.note_kotlin.widget.MyItemDecoration
import com.youth.banner.indicator.CircleIndicator

class RestaurantActivity:AppCompatActivity() ,IFoodListener{

    lateinit var binding:ActivityRestaurantBinding
    lateinit var hotel: Hotel
     var position:Int = -1
    lateinit var foodAdapter: FoodAdapter
    var  list = App.getFoodListNum()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant)

        binding.banner.addBannerLifecycleObserver(this@RestaurantActivity) //添加生命周期观察者
            .setAdapter(ImageAdapter(App.getPicList()))
            .setIndicator(CircleIndicator(this@RestaurantActivity))
        binding.banner.setBannerGalleryMZ(0, 0.8f)

        position = intent.getIntExtra("position",-1)
        hotel = intent.getSerializableExtra("hotel") as Hotel;

        if (position>=0){
            hotel = App.getHotelList()[position]
        }
        foodAdapter = FoodAdapter(this@RestaurantActivity,this)
        binding.rlFood.layoutManager = LinearLayoutManager(this@RestaurantActivity)
        binding.rlFood.addItemDecoration(MyItemDecoration(10))
        binding.rlFood.adapter = foodAdapter;
        foodAdapter.setNewData(App.getFoodList())
        foodAdapter.notifyDataSetChanged()

        binding.tvContinue.setOnClickListener {
            var intent = Intent(this@RestaurantActivity,CheckoutActivity::class.java)
            intent.putExtra("hotel",hotel)
            App.foodlist = list
            startActivity(intent)
        }

    }

    override fun add(food: Food) {

        for (i in 0..App.getFoodListNum().size){
            if (food.picUrl.equals(App.getFoodList()[i].picUrl)){
                var num:Int = list[i].num+1
                list[i].num = num
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
                foodAdapter.setNewData(list)
                foodAdapter.notifyDataSetChanged()
                return
            }
        }
    }


}