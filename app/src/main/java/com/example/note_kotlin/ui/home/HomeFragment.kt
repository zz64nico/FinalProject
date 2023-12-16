package com.example.note_kotlin.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_kotlin.App
import com.example.note_kotlin.activity.RestaurantActivity
import com.example.note_kotlin.adapter.HotelAdapter
import com.example.note_kotlin.adapter.IHotelListener
import com.example.note_kotlin.adapter.ImageAdapter
import com.example.note_kotlin.databinding.FragmentHomeBinding
import com.example.note_kotlin.entity.Hotel
import com.example.note_kotlin.widget.MyItemDecoration
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.youth.banner.indicator.CircleIndicator


class HomeFragment : Fragment() ,IHotelListener{

    private var _binding: FragmentHomeBinding? = null
    lateinit var hotelAdapter: HotelAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        hotelAdapter = HotelAdapter(requireActivity(),this)
        binding.rlHotel.layoutManager = LinearLayoutManager(requireActivity())
        binding.rlHotel.addItemDecoration(MyItemDecoration(10))
        binding.rlHotel.adapter = hotelAdapter;
        hotelAdapter.setNewData(App.getHotelList())
        hotelAdapter.notifyDataSetChanged()
        getHotel()
        getBanner()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getHotel(){
        val db = FirebaseFirestore.getInstance()
        val collectionRef: CollectionReference = db.collection("nam5")
        val query: Query = collectionRef
       try {
           query.get()
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       for (document in task.result.documents) {
                           val title = document.getString("title") // "name"为字段名称
                           Log.d("HoemGragment", "title: $title")
                       }
                   } else {
                       Log.w("TAG", "Error getting documents.", task.exception)
                       getBanner()
                   }
               }
       }catch (e:Exception){
           getBanner()
       }
    }

    fun getBanner(){
        val stringList: MutableList<String?> = ArrayList()
        for (i in 0..5){
            stringList.add(App.getHotelList().get(i).picUrl)
        }
        binding.banner.addBannerLifecycleObserver(activity) //添加生命周期观察者
            .setAdapter(ImageAdapter(stringList))
            .setIndicator(CircleIndicator(activity))
        binding.banner.setBannerGalleryMZ(0, 0.8f)

        binding.banner.setOnBannerListener { data, position ->
            var intent = Intent(requireActivity(),RestaurantActivity::class.java)
            intent.putExtra("position",position)
            startActivity(intent)
        }
    }

    override fun onHomeClick(hotel: Hotel) {
        var intent = Intent(requireActivity(),RestaurantActivity::class.java)
        intent.putExtra("hotel",hotel)
        startActivity(intent)
    }
}