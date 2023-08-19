package id.siandalan.app.features.login.presentation.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.siandalan.app.common.utils.convertToCamelCase
import id.siandalan.app.databinding.AdapterModuleBinding
import id.siandalan.app.features.login.domain.model.ModuleItem

class ModuleAdapter (
    private val checkNominal: (ModuleItem) -> Unit
) : RecyclerView.Adapter<ModuleAdapter.ViewHolder>() {

    private var data = ArrayList<ModuleItem>()

    fun setData(promoList: List<ModuleItem>) {
        data.clear()
        data.addAll(promoList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {

            tvName.text = data[position].nama?.convertToCamelCase()

            root.setOnClickListener {
                checkNominal(data[position])
            }

            if (data[position].isSelected){
                ivCheck.visibility = VISIBLE
            } else {
                ivCheck.visibility = GONE
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterModuleBinding) : RecyclerView.ViewHolder(view.root)


}