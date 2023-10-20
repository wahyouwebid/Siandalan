package id.siandalan.app.features.home.presentation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.common.navigation.Navigation
import id.siandalan.app.common.utils.Constant
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.FragmentHomeBinding
import id.siandalan.app.features.home.domain.model.HomeItem
import id.siandalan.app.features.home.presentation.adapter.HomeAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        uikitChart.setupChart()
    }

    override fun setupViewModel() {
        viewModel.getDataHome()
        viewModel.home.observe(viewLifecycleOwner) { state ->
            when(state) {
                is BaseResultState.Success -> onSuccess(state.data)
                is BaseResultState.Loading -> onLoading(true)
                is BaseResultState.Error -> onError(state.error)
            }
        }
    }

    private fun onSuccess(data: HomeItem) = with(binding) {
        uikitDashboard.setNewRequest(data.request.toString())
        uikitDashboard.setProgress(data.process.toString())
        uikitDashboard.setVerified(data.draft.toString())
        uikitDashboard.setDraftApproval(data.draftApprove.toString())
        uikitDashboard.setDone(data.finish.toString())
        uikitDashboard.setPending(data.long.toString())

        uikitChart.setDataPieChart(data)
        uikitChart.setDataBarChart(data)
        uikitError.hide()

        onLoading(false)
        setDataApproval(data)
    }

    private fun setDataApproval(data: HomeItem) = with(binding) {
        val navigation = activity?.findNavController(R.id.nav_host_main)
        val dataLisDoc = if (data.dataDrafted?.isEmpty() == true) {
            Constant.DataParcelize.DOCUMENT.name to data.docApprove
        } else {
            Constant.DataParcelize.DOCUMENT.name to data.docDrafted
        }

        val dataRole = if (data.dataDrafted?.isEmpty() == true) {
            Constant.DataParcelize.ROLE.name to Constant.Role.DIREKTUR.name
        } else {
            Constant.DataParcelize.ROLE.name to Constant.Role.KASUBDIT.name
        }

        val homeAdapter = HomeAdapter { draftItem ->
            navigation?.navigate(
                Navigation.HOME_TO_DETAIL.id,
                bundleOf(
                    Constant.DataParcelize.DATA.name to draftItem,
                    dataLisDoc,
                    dataRole
                )
            )
        }

        if (data.dataDrafted?.isEmpty() == true) {
            rvData.setHasFixedSize(false)
            rvData.isNestedScrollingEnabled = false
            rvData.layoutManager = LinearLayoutManager(context)
            rvData.adapter = homeAdapter

            homeAdapter.setData(data.dataApproved)
            homeAdapter.notifyItemRangeChanged(0, homeAdapter.itemCount)

            if (data.dataApproved?.isEmpty() == true) {
                rvData.hide()
                uikitEmpty.show()
            } else {
                rvData.show()
                uikitEmpty.hide()
            }
        } else {
            rvData.setHasFixedSize(false)
            rvData.isNestedScrollingEnabled = false
            rvData.layoutManager = LinearLayoutManager(context)
            rvData.adapter = homeAdapter

            homeAdapter.setData(data.dataDrafted)
            homeAdapter.notifyItemRangeChanged(0, homeAdapter.itemCount)

            if (data.dataDrafted?.isEmpty() == true) {
                rvData.hide()
                uikitEmpty.show()
            } else {
                rvData.show()
                uikitEmpty.hide()
            }
        }
    }

    private fun onLoading(isLoading: Boolean) = with(binding){
        uikitLoading.setLoadingHome(isLoading)
        if (isLoading) uikitError.hide()
    }

    private fun onError(error: Throwable) = with(binding){
        uikitError.show()
        uikitError.setError(error) {
            viewModel.getDataHome()
        }
        onLoading(false)
    }

}