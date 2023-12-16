package com.example.note_kotlin

import android.net.Uri
import com.example.note_kotlin.entity.Food
import com.example.note_kotlin.entity.Hotel
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.Date


object App {
    var commonUri: Uri? = null
    var user: FirebaseUser? = null
    var headUrl :String? = null;
    var name :String? = null;

    var foodlist:MutableList<Food> = ArrayList<Food>()
    var foodlistOrder:MutableList<Food> = ArrayList<Food>()

    public fun getHotelList():MutableList<Hotel>{
        var list = ArrayList<Hotel>()
        list.add(Hotel("TaoYuanXiaochu","2023/12/13","https://img2.lepucdn.cn/lepu-upload/2019/06/19/0f3d5153f2dcf271c3fa21c15bcc47fa.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        list.add(Hotel("Nongjia","2023/12/13","https://hiphotos.baidu.com/map/pic/item/7dd98d1001e939017995995778ec54e737d196c7.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        list.add(Hotel("Four Seasons Hotel","2023/12/13","https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/map/pic/item/0b7b02087bf40ad13d0c4f6a5f2c11dfa8ecce4b.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        list.add(Hotel("Double Happiness Hotel","2023/12/13","https://img2.lepucdn.cn/lepu-upload/2019/11/04/240540af393ae1576d32e6c5d86a399f.jpg@%21original.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        list.add(Hotel("Pot Pot Xiang Restaurant","2023/12/13","https://p1.meituan.net/deal/d09e62cbfb3ada280ffa0b354e009cb0662810.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        list.add(Hotel("Red Cloud Restaurant","2023/12/13","https://p1.meituan.net/deal/e6a447ffb5046de49ec7f7f014d79674168412.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        list.add(Hotel("Shuxiang family","2023/12/13","https://img2.lepucdn.cn/lepu-upload/2019/05/04/ef5772e5e50f8844cd6b68eb41c8be3d.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        list.add(Hotel("snack","2023/12/13","https://p0.meituan.net/deal/0c355392408c2a77f84e8b23db14736c293955.jpg","Welcome to Food Paradise, a stylish restaurant in the heart of the city that offers a whole new dining experience. Our restaurant is spacious with plenty of seating and comfortable for every occasion. Our decor is modern and simple, creating an elegant and comfortable dining atmosphere.","","Dashiba, Jiangbei District, Chongqing","34"))
        return list;
    }

    fun getPicList():List<String>{
        var stringList = ArrayList<String>()
        stringList.add("https://photo.16pic.com/00/19/08/16pic_1908868_b.jpg")
        stringList.add("https://pic3.zhimg.com/80/v2-6ab0904289e90722145e4107bff24d0e_720w.webp")
        stringList.add("https://pic3.zhimg.com/80/v2-6ab0904289e90722145e4107bff24d0e_720w.webp")
        return stringList;
    }
    fun getFoodList():MutableList<Food>{
        var stringList = ArrayList<Food>()
        stringList.add(Food("Chicken","https://s.boohee.cn/house/upload_food/2022/1/18/slim_1cfe23ebcba74e6486fda4434c8027fd.jpg",6,1))
        stringList.add(Food("Egg","https://s.boohee.cn/house/upload_food/2022/1/18/slim_mid_photo_url_e1122f4bed0312a354e97094e4e230a3.jpg",6,1))
        stringList.add(Food("shrimp","https://s.boohee.cn/house/upload_food/2021/8/18/mid_photo_url_8d2150e70cd0d7a6456f0cbdf1214b75.jpg",4,1))
        stringList.add(Food("Steamed bun","https://s.boohee.cn/house/upload_food/2022/1/18/slim_mid_photo_url_1945bc70b243079727cfd0670f1b753b.jpg",5,1))
        stringList.add(Food("Millet gruel","https://s.boohee.cn/house/upload_food/2022/1/18/slim_mid_photo_url_big_photo_url_ef353b12d4e89108a469f0a38dff4404.jpg",4,1))
        return stringList;
    }
    fun getFoodListNum():MutableList<Food>{
        var stringList = ArrayList<Food>()
        stringList.add(Food("Chicken","https://s.boohee.cn/house/upload_food/2022/1/18/slim_1cfe23ebcba74e6486fda4434c8027fd.jpg",6,1))
        stringList.add(Food("Egg","https://s.boohee.cn/house/upload_food/2022/1/18/slim_mid_photo_url_e1122f4bed0312a354e97094e4e230a3.jpg",6,1))
        stringList.add(Food("shrimp","https://s.boohee.cn/house/upload_food/2021/8/18/mid_photo_url_8d2150e70cd0d7a6456f0cbdf1214b75.jpg",4,1))
        stringList.add(Food("Steamed bun","https://s.boohee.cn/house/upload_food/2022/1/18/slim_mid_photo_url_1945bc70b243079727cfd0670f1b753b.jpg",5,1))
        stringList.add(Food("Millet gruel","https://s.boohee.cn/house/upload_food/2022/1/18/slim_mid_photo_url_big_photo_url_ef353b12d4e89108a469f0a38dff4404.jpg",4,1))
        return stringList;
    }

    fun getDate(format:String):String{
        val dateFormat = SimpleDateFormat(format)
        val today: String = dateFormat.format(Date())
        return today;
    }
}