package com.example.note_kotlin.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView.OnDateChangeListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.note_kotlin.MainApplication
import com.example.note_kotlin.databinding.FragmentSlideshowBinding
import com.example.note_kotlin.entity.Order
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Random


class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.calendar.setOnDateChangeListener(OnDateChangeListener { view, year, month, day ->
           var time =  showTime(
                year,
                month,
                day
            )
            var list = MainApplication.instance?.dbManager?.findAll(Order::class.java)
            if (list ==null){
                binding.tvMoney.text = "$ 0"
                return@OnDateChangeListener
            }
            var total:Int = 0
            for(i in 0..list.size-1){
                if (time.equals(list[i].time)){
                    total+=list[i].money?.toInt()!!
                }

            }
            binding.tvMoney.text = "$ ${total}"
        })
        initBarChart(binding.barChart); //初始化一个柱状图
        binding.barChart.setData(setBarData()); //给柱状图添加数据
        binding.barChart.invalidate();
        return root
    }

    fun setBarData(): BarData? {
        val entries: MutableList<BarEntry> =
            ArrayList() //定义一个数据容器
        //生成随机数数据
        for (i in 1..31) {
            entries.add(BarEntry(i.toFloat(), getMoney(i)))
        }
        val barDataSet = BarDataSet(entries, "Money Spend")
        return BarData(barDataSet) //返回可用于柱状图的数据
    }


    fun getMoney( time:Int):Float{
        var list = MainApplication.instance?.dbManager?.findAll(Order::class.java)
        if (list ==null){
            return 0f
        }
        var total:Int = 0
        for(i in 0 until list.size){
            if (time<9){
                if (list[i].time?.substring(4,5).equals(time.toString())){
                    total+=list[i].money?.toInt()!!
                }
            }else{
                if (list[i].time?.substring(3,5).equals(time.toString())){
                    total+=list[i].money?.toInt()!!
                }
            }
        }
        return total.toFloat()
    }

    fun initBarChart(barChart: BarChart): BarChart? {
        barChart.setDrawBarShadow(false) // 设置每条柱子的阴影不显示
        barChart.setDrawValueAboveBar(true) // 设置每条柱子的数值显示
        val xAxis = barChart.xAxis // 获取柱状图的x轴
        val yAxisLeft = barChart.axisLeft // 获取柱状图左侧的y轴
        val yAxisRight = barChart.axisRight // 获取柱状图右侧的y轴
        setAxis(xAxis, yAxisLeft, yAxisRight) //调用方法设置柱状图的轴线
        return barChart
    }

    fun setAxis(xAxis: XAxis, leftAxis: YAxis, rightAxis: YAxis) {
        xAxis.position = XAxis.XAxisPosition.BOTTOM // 这里设置x轴在柱状图底部显示
        xAxis.axisLineWidth = 1f //设置x轴宽度
        xAxis.axisMinimum = 0f //设置x轴从0开始绘画
        xAxis.setDrawAxisLine(true) //设置x轴的轴线显示
        xAxis.setDrawGridLines(false) //设置x轴的表格线不显示
        xAxis.isEnabled = true // 设置x轴显示
        leftAxis.axisMinimum = 0f //设置y轴从0刻度开始
        leftAxis.setDrawGridLines(false) // 这里设置左侧y轴不显示表格线
        leftAxis.setDrawAxisLine(true) // 这里设置左侧y轴显示轴线
        leftAxis.axisLineWidth = 1f //设置y轴宽度
        leftAxis.isEnabled = true //设置左侧的y轴显示
        rightAxis.axisMinimum = 0f //设置y轴从0刻度开始
        rightAxis.setDrawGridLines(false) // 这里设置右侧y轴不显示表格线
        rightAxis.setDrawAxisLine(true) // 这里设置右侧y轴显示轴线
        rightAxis.axisLineWidth = 1f //设置右侧y轴宽度
        rightAxis.isEnabled = true //设置右侧的y轴显示
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showTime(year: Int, month: Int, day: Int):String {
        // 根据年月日获取时间
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        // 格式化时间
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date: String = sdf.format(calendar.getTime())

        return date;
    }
}