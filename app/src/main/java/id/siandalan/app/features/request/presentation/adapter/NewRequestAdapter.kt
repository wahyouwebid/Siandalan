package id.siandalan.app.features.request.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.siandalan.app.R
import id.siandalan.app.common.utils.capitalizeFirstLetter
import id.siandalan.app.common.utils.dateFormatComplete
import id.siandalan.app.databinding.AdapterRequestApprovalNewBinding
import id.siandalan.app.features.home.presentation.model.Category
import id.siandalan.app.features.request.domain.model.RequestItem

class NewRequestAdapter(
    private val action: (RequestItem.DataApprovedItem) -> Unit
) : RecyclerView.Adapter<NewRequestAdapter.ViewHolder>() {

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
            tvCategory.text = item.category
            tvDetailStatus.text = item.status?.capitalizeFirstLetter()
            tvDetailNo.text = item.no
            tvDetailName.text = item.name
            tvDetailProjectName.text = item.projectName
            tvDetailCategory.text = item.category
            tvDetailConsultant.text = item.consultant
            tvDetailDate.text = item.dateProcess?.dateFormatComplete()
            byCategory(holder.view, item)
            root.setOnClickListener {
                action.invoke(item)
            }

            btnDetail.setOnClickListener {
                elDetail.toggle()
                if (elDetail.isExpanded) {
                    btnDetail.text = btnDetail.context.getString(R.string.title_show_less)
                    btnDetail.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0,
                        R.drawable.ic_up, 0
                    )
                } else {
                    btnDetail.text = btnDetail.context.getString(R.string.title_show_more)
                    btnDetail.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0,
                        R.drawable.ic_bottom, 0
                    )
                }
            }
        }
    }

    private fun byCategory(
        binding: AdapterRequestApprovalNewBinding,
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
        AdapterRequestApprovalNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterRequestApprovalNewBinding) : RecyclerView.ViewHolder(view.root)

}