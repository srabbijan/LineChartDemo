package com.example.linechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpLineChart()
        setDataToLineChart()
    }
    private fun setUpLineChart() {
        with(lineChart) {

            axisRight.isEnabled = false
            animateX(1, Easing.EaseInSine)

            description.isEnabled = false

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = MyAxisFormatter()
            xAxis.granularity = 1F
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            axisLeft.setDrawGridLines(true)
            extraRightOffset = 1f

            legend.isEnabled = true
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.form = Legend.LegendForm.DEFAULT


        }
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        private var weekName = arrayListOf("Week 1", "Week 2", "Week 3", "Week 4", "Week 5")

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < weekName.size && index>=0) {
                weekName[index]
                } else {
                    ""
                }

        }

    }

    private fun setDataToLineChart() {


        val weekOneSales = LineDataSet(saleData(), "Sale")
        weekOneSales.lineWidth = 1.5f
        weekOneSales.setDrawValues(false)
        weekOneSales.setDrawCircles(false)
        weekOneSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekOneSales.color = ContextCompat.getColor(this, R.color.blue)

        val weekTwoSales = LineDataSet(purchaseData(), "Purchase")
        weekTwoSales.lineWidth = 1.5f
        weekTwoSales.setDrawValues(false)
        weekTwoSales.setDrawCircles(false)
        weekTwoSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekTwoSales.color = ContextCompat.getColor(this, R.color.colorYellow)

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekOneSales)
        dataSet.add(weekTwoSales)

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.invalidate()

    }

    private fun saleData(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 15f))
        sales.add(Entry(1f, 16f))
        sales.add(Entry(2f, 13f))
        sales.add(Entry(3f, 22f))
        sales.add(Entry(4f, 20f))
        return sales
    }

    private fun purchaseData(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 11f))
        sales.add(Entry(1f, 13f))
        sales.add(Entry(2f, 18f))
        sales.add(Entry(3f, 16f))
        sales.add(Entry(4f, 22f))
        return sales
    }
}