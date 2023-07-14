package id.siandalan.app.features.request.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.common.navigation.Navigation
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.FragmentRequestBinding
import id.siandalan.app.features.request.domain.model.RequestItem
import id.siandalan.app.features.request.domain.state.RequestResultState
import id.siandalan.app.features.request.presentation.adapter.RequestAdapter

@AndroidEntryPoint
class RequestFragment : BaseFragment<FragmentRequestBinding>(FragmentRequestBinding::inflate) {

    private val viewModel: RequestViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val requestAdapter: RequestAdapter by lazy {
        RequestAdapter { navigation?.navigate(Navigation.REQUEST_TO_DETAIL.id) }
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setupAdapter()
    }

    override fun setupViewModel() {
        viewModel.getDataRequest()
        viewModel.request.observe(viewLifecycleOwner) { state ->
            when(state) {
                is RequestResultState.Success -> onSuccess(state.data)
                is RequestResultState.Loading -> onLoading(true)
                is RequestResultState.Error -> onError(state.error)
            }
        }
    }

    private fun onSuccess(data: RequestItem) = with(binding) {
        requestAdapter.setData(data.dataApproved)
        requestAdapter.notifyItemRangeChanged(0, requestAdapter.itemCount)

        uikitError.hide()
        onLoading(false)
    }

    private fun onLoading(isLoading: Boolean) = with(binding){
        uikitLoading.setLoadingApproval(isLoading)
        if (isLoading) uikitError.hide()
    }

    private fun onError(error: Throwable) = with(binding){
        uikitError.show()
        uikitError.setError(error.message.toString()) {
            viewModel.getDataRequest()
        }
        onLoading(false)
    }

    private fun setupAdapter() = with(binding) {
        rvData.setHasFixedSize(false)
        rvData.isNestedScrollingEnabled = false
        rvData.layoutManager = LinearLayoutManager(context)
        rvData.adapter = requestAdapter
    }

}