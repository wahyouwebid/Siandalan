package id.siandalan.app.features.request.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.siandalan.app.R
import id.siandalan.app.common.utils.capitalizeFirstLetter
import id.siandalan.app.common.utils.dateFormat
import id.siandalan.app.common.utils.dateFormatComplete
import id.siandalan.app.databinding.AdapterRequestApprovalBinding
import id.siandalan.app.features.home.presentation.model.Category
import id.siandalan.app.features.request.domain.model.RequestItem

class RequestAdapter(
    private val action: (RequestItem.DataApprovedItem) -> Unit
) : RecyclerView.Adapter<RequestAdapter.ViewHolder>() {

    private var data = ArrayList<RequestItem.DataApprovedItem>()

    fun setData(itemList: List<RequestItem.DataApprovedItem>?) {
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
            tvUpdateAt.text = item.applicationLaterDate?.dateFormat()
            tvStatus.text = "Status: ${item.status?.capitalizeFirstLetter()}"
            tvConsultant.text = item.consultant
            byCategory(holder.view, item)
            root.setOnClickListener {
                action.invoke(item)
            }
        }
    }

    private fun byCategory(
        binding: AdapterRequestApprovalBinding,
        item: RequestItem.DataApprovedItem
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
        AdapterRequestApprovalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterRequestApprovalBinding) : RecyclerView.ViewHolder(view.root)

}