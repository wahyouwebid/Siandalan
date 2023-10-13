package id.siandalan.app.features.detail.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.siandalan.app.databinding.AdapterDaftarDokumenBinding
import id.siandalan.app.features.home.domain.model.HomeItem

class DetailDokumenAdapter(
    private val action: (HomeItem.DataApprovedItem.DocumentItem) -> Unit
) : RecyclerView.Adapter<DetailDokumenAdapter.ViewHolder>() {

    private var data = ArrayList<HomeItem.DataApprovedItem.DocumentItem>()

    fun setData(itemList: List<HomeItem.DataApprovedItem.DocumentItem>?) {
        data.clear()
        itemList?.let { data.addAll(it) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            tvTitle.text = item.titleName
            tvName.text = item.documentName
            root.setOnClickListener {
                action.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterDaftarDokumenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterDaftarDokumenBinding) : RecyclerView.ViewHolder(view.root)

}