package com.example.note_kotlin.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.note_kotlin.MainApplication
import com.example.note_kotlin.adapter.OrderAdapter
import com.example.note_kotlin.databinding.FragmentGalleryBinding
import com.example.note_kotlin.entity.Order
import com.example.note_kotlin.widget.MyItemDecoration

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    lateinit var orderAdapter: OrderAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        orderAdapter = OrderAdapter()
        binding.rlOrder.layoutManager = LinearLayoutManager(requireActivity())
        binding.rlOrder.addItemDecoration(MyItemDecoration(10))
        binding.rlOrder.adapter = orderAdapter;
        var list: MutableList<Order>? = MainApplication.instance?.dbManager?.findAll(Order::class.java)
        orderAdapter.setNewData(list)
        orderAdapter.notifyDataSetChanged()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}