package id.siandalan.app.features.home.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.common.navigation.Navigation
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.FragmentHomeBinding
import id.siandalan.app.features.home.domain.model.HomeItem
import id.siandalan.app.features.home.domain.state.HomeResultState
import id.siandalan.app.features.home.presentation.adapter.HomeAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        uikitChart.setupChart()
        setupAdapter()
    }

    override fun setupViewModel() {
        viewModel.getDataHome()
        viewModel.home.observe(viewLifecycleOwner) { state ->
            when(state) {
                is HomeResultState.Success -> onSuccess(state.data)
                is HomeResultState.Loading -> onLoading(true)
                is HomeResultState.Error -> onError(state.error)
            }
        }
    }

    private fun onSuccess(data: HomeItem) = with(binding) {
        uikitDashboard.setNewRequest(data.request.toString())
        uikitDashboard.setProgress(data.process.toString())
        uikitDashboard.setVerified(data.approved.toString())
        uikitDashboard.setDraftApproval(data.draft.toString())
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
        val homeAdapter = HomeAdapter { navigation?.navigate(Navigation.HOME_TO_DETAIL.id) }

        rvData.setHasFixedSize(false)
        rvData.isNestedScrollingEnabled = false
        rvData.layoutManager = LinearLayoutManager(context)
        rvData.adapter = homeAdapter

        homeAdapter.setData(data.dataApproved)
        homeAdapter.notifyItemRangeChanged(0, homeAdapter.itemCount)
    }

    private fun onLoading(isLoading: Boolean) = with(binding){
        uikitLoading.setLoadingHome(isLoading)
        if (isLoading) uikitError.hide()
    }

    private fun onError(error: Throwable) = with(binding){
        uikitError.show()
        uikitError.setError(error.message.toString()) {
            viewModel.getDataHome()
        }
        onLoading(false)
    }

    private fun setupAdapter() = with(binding) {

    }

}