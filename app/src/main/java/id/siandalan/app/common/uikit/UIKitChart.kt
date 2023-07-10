package id.siandalan.app.common.uikit

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import id.siandalan.app.R
import id.siandalan.app.databinding.UikitChartBinding
import id.siandalan.app.databinding.UikitDashboardBinding
import id.siandalan.app.databinding.UikitToolbarBinding
import id.siandalan.app.features.home.domain.model.DataHome

class UIKitChart(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitChartBinding = UikitChartBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setupChart() = with(binding) {
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.isRotationEnabled = true
        pieChart.setEntryLabelTextSize(12f)
        pieChart.setUsePercentValues(true)
        pieChart.setDrawEntryLabels(false)
    }

    fun setData(data: DataHome) = with(binding) {
        val pieEntries = listOf(
            PieEntry(data.request?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_new_request)),
            PieEntry(data.process?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_onprogress)),
            PieEntry(data.approved?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_verified)),
            PieEntry(data.draft?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_draft_approval)),
            PieEntry(data.finish?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_done)),
            PieEntry(data.long?.toFloat() ?: 0f, context.getString(R.string.title_dashboard_pending)),
        )

        val colors = listOf(
            ContextCompat.getColor(context, R.color.colorDashboard1),
            ContextCompat.getColor(context, R.color.colorDashboard2),
            ContextCompat.getColor(context, R.color.colorDashboard3),
            ContextCompat.getColor(context, R.color.colorDashboard4),
            ContextCompat.getColor(context, R.color.colorDashboard5),
            ContextCompat.getColor(context, R.color.colorDashboard6),
        )

        val dataSet = PieDataSet(pieEntries, "")
        dataSet.colors = colors

        val pieData = PieData(dataSet)
        pieData.setDrawValues(true)
        pieData.setValueTextSize(14f)
        pieData.setValueTextColor(Color.WHITE)
        pieData.setValueFormatter(PercentFormatter())
        pieChart.data = pieData
        pieChart.invalidate()
    }

}