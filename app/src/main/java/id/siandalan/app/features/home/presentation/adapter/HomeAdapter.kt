package id.siandalan.app.features.home.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.siandalan.app.R
import id.siandalan.app.databinding.AdapterApprovalBinding
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
            tvDate.text = item.dateProcess
            tvCategory.text = item.category
            byCategory(holder.view, item)
            root.setOnClickListener {
                action.invoke(item)
            }
        }
    }

    private fun byCategory(
        binding: AdapterApprovalBinding,
        item: HomeItem.DataApprovedItem
    ) {
        with(binding){
            when (item.category) {
                Category.Rendah.name -> {
                    tvCategory.background = ContextCompat.getDrawable(tvCategory.context,
                        R.drawable.bg_rounded_low)
                }

                Category.Sedang.name -> {
                    tvCategory.background = ContextCompat.getDrawable(tvCategory.context,
                        R.drawable.bg_rounded_medium)
                }

                Category.Tinggi.name -> {
                    tvCategory.background = ContextCompat.getDrawable(tvCategory.context,
                        R.drawable.bg_rounded_high)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterApprovalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterApprovalBinding) : RecyclerView.ViewHolder(view.root)

}