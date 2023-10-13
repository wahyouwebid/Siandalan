package id.siandalan.app.features.detail.presentation

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.BuildConfig
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.common.uikit.UIKitPopUp
import id.siandalan.app.common.uikit.UIKitPopUpFragment
import id.siandalan.app.common.utils.Constant
import id.siandalan.app.common.utils.dateFormat
import id.siandalan.app.common.utils.dateFormatCompleteWithSecond
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.parcelable
import id.siandalan.app.common.utils.show
import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.databinding.FragmentDetailBinding
import id.siandalan.app.features.home.domain.model.HomeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL


@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val data: HomeItem.DataApprovedItem? by lazy {
        arguments?.parcelable(Constant.DataParcelize.DATA.name)
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        uikitToolbar.setToolbar(data?.no + " : " + data?.projectName) {
            navigation?.navigateUp()
        }

        cvDataPengajuan.setOnClickListener {
            elDataPengajuan.toggle()
            if (elDataPengajuan.isExpanded) {
                tvDataPengajuan.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_up, 0
                )
            } else {
                tvDataPengajuan.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_bottom, 0
                )
            }
        }

        cvDaftarDokumen.setOnClickListener {
            elDaftarDokumen.toggle()
            if (elDaftarDokumen.isExpanded) {
                tvDaftarDokumen.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_up, 0
                )
            } else {
                tvDaftarDokumen.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_bottom, 0
                )
            }
        }

        cvPembayaranPnbp.setOnClickListener {
            elPembayaranPnbp.toggle()
            if (elPembayaranPnbp.isExpanded) {
                tvPembayaranPnbp.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_up, 0
                )
            } else {
                tvPembayaranPnbp.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_bottom, 0
                )
            }
        }

        cvSkAndalan.setOnClickListener {
            elSkAndalan.toggle()
            if (elSkAndalan.isExpanded) {
                tvSkAndalan.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_up, 0
                )
            } else {
                tvSkAndalan.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0,
                    R.drawable.ic_bottom, 0
                )
            }
        }

        btnRevise.setOnClickListener {
            if (!etRevise.text.isNullOrEmpty()) {
                viewModel.postRevise(data?.id, etRevise.text.toString())
            } else {
                Toast.makeText(requireContext(), "Form revisi tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDataPengajuan() = with(binding){
        tvPengajuanNib.text = data?.nib
        tvPengajuanName.text = data?.name
        tvPengajuanEmail.text = data?.emailPerusahaan
        tvPengajuanPhone.text = data?.nomorTelponPerseroan
        tvPengajuanLeaderName.text = data?.leaderName
        tvPengajuanPosition.text = data?.leaderPosition

        tvPengajuanCategory.text = data?.category
        tvPengajuanSubCategory.text = data?.subCategory
        tvPengajuanUkuran.text = data?.capacity
        tvPengajuanKlasifikasi.text = data?.classification
        tvPengajuanKapasitasRill.text = data?.kapasitasRiil
        tvPengajuanNamaProyek.text = data?.projectName
        tvPengajuanPembangunan.text = data?.pembangunan
        tvPengajuanAlamatProyek.text = data?.projectAddress
        tvPengajuanProvinsi.text = data?.projectProvince
        tvPengajuanKota.text = data?.projectRegency
        tvPengajuanNoSuratPermohonan.text = data?.applicationLetterNo
        tvPengajuanTglSuratPermohonan.text = data?.applicationLetterDate?.dateFormat()
    }

    private fun setPembayaranPnbp() = with(binding) {
        tvPembayaranNoAndalin.text = data?.noAndalalin
        tvPembayaranHarga.text = data?.price
        tvKodeBilling.text = data?.pengajuanKodeBilling
        tvKodeBilling.text = data?.billingCode
        tvTanggalPembayaran.text = data?.paymentDate?.dateFormatCompleteWithSecond()
        tvKodeBilling.text = data?.billingCode
    }

    private fun setSkAndalan() = with(binding) {
        val sessions = Sessions(requireContext())
        val module = sessions.getString(Sessions.module)
        val url = "${BuildConfig.baseUrl}/modules/${module}/public/${data?.finalLetterFile}"
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val input = URL(url).openStream()
                pdfSkAndalan.fromStream(input)
                    .swipeHorizontal(false)
                    .enableSwipe(true)
                    .enableAnnotationRendering(true)
                    .onDrawAll { canvas, pageWidth, pageHeight, _ ->
                        val bitmap =
                            Bitmap.createBitmap(
                                canvas.width,
                                canvas.height,
                                Bitmap.Config.ARGB_8888
                            )
                        val paint = Paint()
                        paint.color = Color.BLACK
                        canvas.drawBitmap(bitmap, pageWidth, pageHeight, paint)
                    }
                    .onLoad {

                    }
                    .onRender { }
                    .linkHandler(DefaultLinkHandler(pdfSkAndalan))
                    .load()

            } catch (e: Exception) {
                Log.e("error", e.toString())
            }
        }
    }

    override fun setupViewModel() {
        setDataPengajuan()
        setPembayaranPnbp()
        setSkAndalan()
        onLoading(false)

        viewModel.revise.observe(viewLifecycleOwner) {
            when(it) {
                is BaseResultState.Loading -> onLoading(true)
                is BaseResultState.Success -> {
                    Toast.makeText(requireContext(), "Revisi Berhasil!!", Toast.LENGTH_SHORT).show()
                }
                is BaseResultState.Error -> onError()
            }
        }
    }

    private fun onLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            uikitLoading.show()
            uikitLoading.setLoadingProgress(true)
        } else {
            uikitLoading.hide()
            uikitLoading.setLoadingProgress(false)
        }
    }


    private fun onError() = with(binding){
        uikitLoading.hide()
        UIKitPopUpFragment(this@DetailFragment).showPopupError {
            viewModel.postRevise(data?.id, etRevise.text.toString())
        }
    }

}