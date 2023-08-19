package id.siandalan.app.features.login.presentation

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.common.base.BaseBottomSheet
import id.siandalan.app.databinding.FragmentModuleBinding
import id.siandalan.app.features.login.domain.model.ModuleItem
import id.siandalan.app.features.login.presentation.adapter.ModuleAdapter

/**
 * Created by wahyouwebid on 15 August 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

@AndroidEntryPoint
class ModuleBottomSheet: BaseBottomSheet<FragmentModuleBinding>(FragmentModuleBinding::inflate) {

    private val viewModel: LoginViewModel by activityViewModels()

    private val dataList : MutableList<ModuleItem> by lazy {
        ArrayList()
    }

    private val moduleAdapter : ModuleAdapter by lazy {
        ModuleAdapter { item -> dataSelected(item)}
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setupListener()
    }

    override fun setupViewModel() {
        viewModel.moduleList.observe(viewLifecycleOwner) {
            setData(it)
        }

        viewModel.moduleSelected.observe(viewLifecycleOwner){
            it?.let {
                setDataSelected(it)
            }
        }
    }

    private fun setData(data: List<ModuleItem>) {
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moduleAdapter
        }

        this.dataList.clear()
        this.dataList.addAll(data)
        moduleAdapter.setData(data)
    }

    private fun dataSelected(item: ModuleItem) {
        viewModel.moduleSelected.postValue(item)
        viewModel.setModuleName(item.nama)
        dismiss()
    }

    private fun setDataSelected(data: ModuleItem){
        val tmp = dataList.map {
            ModuleItem(
                it.id,
                it.nama,
                it.id == data.id
            )
        }
        dataList.clear()
        dataList.addAll(tmp)
        moduleAdapter.setData(dataList)
    }

    private fun setupListener() {
        with(binding){
            ivClose.setOnClickListener {
                dismiss()
            }
        }
    }
}