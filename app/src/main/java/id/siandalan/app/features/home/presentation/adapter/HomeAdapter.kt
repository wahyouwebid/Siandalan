package id.siandalan.app.features.home.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.siandalan.app.R
import id.siandalan.app.common.utils.dateFormat
import id.siandalan.app.common.utils.dateFormatComplete
import id.siandalan.app.databinding.AdapterHomeApprovalBinding
import id.siandalan.app.features.home.domain.model.HomeItem
import id.siandalan.app.features.home.presentation.model.Category

class HomeAdapter(
    private val action: (HomeItem.DataApprovedItem) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var data = ArrayList<HomeItem.DataApprovedItem>()

    fun setData(itemList: List<HomeItem.DataApprovedItem>?) {
        data.clear()
        itemList?.let { data.addAll(it) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            tvNo.text = item.no
            tvName.text = item.name
            tvProjectName.text = item.projectName
            tvDate.text = item.dateProcess?.dateFormatComplete()
            tvCategory.text = item.category
            byCategory(holder.view, item)
            root.setOnClickListener {
                action.invoke(item)
            }
        }
    }

    private fun byCategory(
        binding: AdapterHomeApprovalBinding,
        item: HomeItem.DataApprovedItem
    ) {
        with(binding){
            when (item.category) {
                Category.Rendah.name -> {
                    tvCategory.background = ContextCompat.getDrawable(tvCategory.context,
                        R.drawable.bg_rounded_dashboard_5)
                }

                Category.Sedang.name -> {
                    tvCategory.background = ContextCompat.getDrawable(tvCategory.context,
                        R.drawable.bg_rounded_dashboard_3)
                }

                Category.Tinggi.name -> {
                    tvCategory.background = ContextCompat.getDrawable(tvCategory.context,
                        R.drawable.bg_rounded_dashboard_6)
                }

                else -> {
                    tvCategory.background = ContextCompat.getDrawable(tvCategory.context,
                        R.drawable.bg_rounded_dashboard_2)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterHomeApprovalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterHomeApprovalBinding) : RecyclerView.ViewHolder(view.root)

}