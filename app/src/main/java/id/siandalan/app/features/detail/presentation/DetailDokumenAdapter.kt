package id.siandalan.app.features.detail.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.siandalan.app.common.utils.capitalizeFirstLetter
import id.siandalan.app.databinding.AdapterDaftarDokumenBinding
import id.siandalan.app.features.home.domain.model.HomeItem
import java.lang.Exception

class DetailDokumenAdapter(
    private val action: (HomeItem.DocumentItem) -> Unit
) : RecyclerView.Adapter<DetailDokumenAdapter.ViewHolder>() {

    private var data = ArrayList<HomeItem.DocumentItem>()

    fun setData(itemList: List<HomeItem.DocumentItem>?) {
        data.clear()
        itemList?.let { data.addAll(it) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            tvTitle.text = item.name?.extractName()
            tvName.text = item.fileName?.extractFileName()
            root.setOnClickListener {
                action.invoke(item)
            }
        }
    }

    private fun String.extractFileName(): String? {
        return try {
            val parts = this.split('/')
            val removeUrl = parts.last()
            val partsFileName = removeUrl.split("_-_")
            val fileName = partsFileName.last()
            java.net.URLDecoder.decode(fileName, "UTF-8").capitalizeFirstLetter()
        } catch (e: java.io.UnsupportedEncodingException) {
            this
        }
    }

    private fun String.extractName(): String? {
        return try {
            val string =  this.replace("_", " ")
                .split(" ")
                .joinToString(" ") {
                    it.capitalizeFirstLetter()
                }
            string
        } catch (e: Exception) {
            this
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterDaftarDokumenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterDaftarDokumenBinding) : RecyclerView.ViewHolder(view.root)

}