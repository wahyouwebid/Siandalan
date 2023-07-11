package id.siandalan.app.common.uikit

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import id.siandalan.app.R
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.UikitChartBinding
import id.siandalan.app.features.home.domain.model.HomeItem


class UIKitChart(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitChartBinding = UikitChartBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setupChart() = with(binding) {
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.isRotationEnabled = true
        pieChart.setEntryLabelTextSize(12f)
        pieChart.setDrawEntryLabels(false)
        pieChart.setDrawCenterText(true)
        onListener()
    }

    fun setDataPieChart(data: HomeItem) = with(binding) {
        val pieEntries = listOf(
            PieEntry(data.request?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_new_request)),
            PieEntry(data.process?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_onprogress)),
            PieEntry(data.approved?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_verified)),
            PieEntry(data.draft?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_draft_approval)),
            PieEntry(data.finish?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_done)),
            PieEntry(data.long?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_pending)),
        )

        val dataSet = PieDataSet(pieEntries, "")
        dataSet.colors = getColor()
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val pieData = PieData(dataSet)
        pieData.setDrawValues(true)
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.BLACK)
        pieData.setValueFormatter(PercentFormatter(pieChart))
        pieChart.data = pieData
        pieChart.invalidate()
    }

    fun setDataBarChart(data: HomeItem) = with(binding) {

        val entries = ArrayList<BarEntry>()
        val labelSize = arrayOfNulls<String>(6)

        val dataLabel = listOf(
            context.getString(R.string.title_chart_legend_request),
            context.getString(R.string.title_chart_legend_progress),
            context.getString(R.string.title_chart_legend_draft),
            context.getString(R.string.title_chart_legend_approved),
            context.getString(R.string.title_chart_legend_done),
            context.getString(R.string.title_chart_legend_pending)
        )

        dataLabel.reversed().forEachIndexed { index, name -> labelSize[index] = name }

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labelSize)

        entries.add(BarEntry(5f, data.request?.toFloat() ?: 0f))
        entries.add(BarEntry(4f, data.process?.toFloat() ?: 0f))
        entries.add(BarEntry(3f,data.approved?.toFloat() ?: 0f))
        entries.add(BarEntry(2f, data.draft?.toFloat() ?: 0f))
        entries.add(BarEntry(1f, data.finish?.toFloat() ?: 0f))
        entries.add(BarEntry(0f, data.long?.toFloat() ?: 0f))

        val dataSet = BarDataSet(entries, "Bar Data")
        dataSet.setDrawValues(true)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f
        dataSet.colors = getColor()

        val barData = BarData(dataSet)
        barData.barWidth = 0.9f
        barChart.data = barData
        barChart.setDrawValueAboveBar(true)
        barChart.setFitBars(true)
        barChart.animateY(4500)
        barChart.setFitBars(true)
        barChart.setDrawValueAboveBar(true)
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.axisLeft.isEnabled = true
        barChart.axisRight.isEnabled = true
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.spaceMax = 0.2f
        barChart.invalidate()
    }

    private fun getColor() = listOf(
        ContextCompat.getColor(context, R.color.colorDashboard1),
        ContextCompat.getColor(context, R.color.colorDashboard2),
        ContextCompat.getColor(context, R.color.colorDashboard3),
        ContextCompat.getColor(context, R.color.colorDashboard4),
        ContextCompat.getColor(context, R.color.colorDashboard5),
        ContextCompat.getColor(context, R.color.colorDashboard6),
    )

    private fun onListener() = with(binding) {
        rgChart.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_piechart -> {
                    pieChart.show()
                    clLegendPieChart.show()
                    barChart.hide()
                }

                R.id.rb_barchart -> {
                    pieChart.hide()
                    clLegendPieChart.hide()
                    barChart.show()
                }
            }
        }
    }
}