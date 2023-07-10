package id.siandalan.app.features.home.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.databinding.FragmentHomeBinding
import id.siandalan.app.features.home.domain.model.DataHome
import id.siandalan.app.features.home.domain.state.HomeResultState
import id.siandalan.app.features.home.presentation.adapter.HomeAdapter


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter : HomeAdapter by lazy {
        HomeAdapter()
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setupToolbar()
        setupAdapter()
        binding.uikitChart.setupChart()
    }

    override fun setupViewModel() {
        viewModel.getDataHome()
        viewModel.home.observe(viewLifecycleOwner) { state ->
            when(state) {
                is HomeResultState.Success -> {}
                is HomeResultState.Loading -> {}
                is HomeResultState.Error -> {}
            }
        }

        val data = DataHome(
            2,4,5,12,23,12, 12,
            listOf(
                DataHome.DataApprovedItem(
                    "ANDL-202305008",
                    "ANDL-202305008",
                    "PT NANKAI INDONESIA",
                    "Pembangunan Stasiun Bahan Bakar Umum (SPBU) Dodo Mini",
                    "Rendah",
                    "03-05-2023",
                ),

                DataHome.DataApprovedItem(
                    "ANDL-202305035",
                    "ANDL-202305035",
                    "PT MEDIKALOKA ARCAMANIK",
                    "Pengembangan Rumah Sakit Hermina Arcamanik",
                    "Sedang",
                    "04-05-2023",
                ),

                DataHome.DataApprovedItem(
                    "ANDL-202304213",
                    "ANDL-202304213",
                    "PT. DOK PANTI LAMONGAN",
                    "Kegiatan Pengembangan Reparasi Kapal, Perahu dan Bangunan Terapung",
                    "Tinggi",
                    "28-04-2023",
                ),
            )
        )

        onSuccess(data)
    }

    private fun onSuccess(data: DataHome) = with(binding) {
        uikitDashboard.setNewRequest(data.request.toString())
        uikitDashboard.setProgress(data.process.toString())
        uikitDashboard.setVerified(data.approved.toString())
        uikitDashboard.setDraftApproval(data.draft.toString())
        uikitDashboard.setDone(data.finish.toString())
        uikitDashboard.setPending(data.long.toString())

        homeAdapter.setData(data.dataApproved)

        uikitChart.setData(data)
    }

    private fun setupToolbar() = with(binding) {
        uikitToolbar.setToolbar(getString(R.string.app_name))
    }

    private fun setupAdapter() = with(binding) {
        rvData.layoutManager = LinearLayoutManager(context)
        rvData.adapter = homeAdapter
    }

}