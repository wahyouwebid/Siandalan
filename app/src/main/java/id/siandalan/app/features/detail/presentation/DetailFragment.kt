package id.siandalan.app.features.detail.presentation

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.BuildConfig
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.common.uikit.UIKitPopUpFragment
import id.siandalan.app.common.utils.Constant
import id.siandalan.app.common.utils.dateFormat
import id.siandalan.app.common.utils.dateFormatCompleteWithSecond
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.openDownloadedPDF
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

    private var documentItem: HomeItem.DataApprovedItem.DocumentItem? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            downloadPdf()
        }
        else Toast.makeText(
            activity,
            "Permission is needed to activate this feature.",
            Toast.LENGTH_SHORT
        ).show()
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
            val homeAdapter = DetailDokumenAdapter {
                documentItem = it
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    downloadPdf()
                } else {
                    when {
                        requireContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                            downloadPdf()
                        }
                        shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                            Toast.makeText(
                                requireContext(),
                                "Permission is needed to activate this feature.",
                                Toast.LENGTH_SHORT
                            ).show()
                            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        }
                        else -> {
                            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        }
                    }
                }
            }

            rvDaftarDokumen.setHasFixedSize(false)
            rvDaftarDokumen.isNestedScrollingEnabled = false
            rvDaftarDokumen.layoutManager = LinearLayoutManager(context)
            rvDaftarDokumen.adapter = homeAdapter

            homeAdapter.setData(data?.dataDocument)
            homeAdapter.notifyItemRangeChanged(0, homeAdapter.itemCount)

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
                viewModel.postRevise("1538", etRevise.text.toString())
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

        val sessions = Sessions(requireContext())
        val module = sessions.getString(Sessions.module)

        val urlInvoice = "${BuildConfig.baseUrl}/modules/${module}/public/${data?.finalLetterFile}"
        val urlKuitansi = "${BuildConfig.baseUrl}/modules/${module}/public/${data?.finalLetterFile}"
        setupPdf(urlInvoice, pdfPembayaranInvoice)
        setupPdf(urlKuitansi, pdfPembayaranKuitansi)
    }

    private fun setSkAndalan() {
        val sessions = Sessions(requireContext())
        val module = sessions.getString(Sessions.module)

        val urlSk = "${BuildConfig.baseUrl}/modules/${module}/public/${data?.finalLetterFile}"
        setupPdf(urlSk, binding.pdfSkAndalan)
    }

    private fun setupPdf(url: String, pdfView: PDFView) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val input = URL(url).openStream()
                pdfView.fromStream(input)
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
                    .linkHandler(DefaultLinkHandler(pdfView))
                    .load()

            } catch (e: Exception) {
                Log.e("error", e.toString())
            }
        }
    }

    private fun downloadPdf() {
        val urlPdf = "${BuildConfig.baseUrl}/modules/pusat/public/${documentItem?.documentLink}"
        val request = DownloadManager.Request(Uri.parse(urlPdf))
            .setTitle("${documentItem?.documentName}")
            .setDescription("Downloading ${documentItem?.documentName}")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${documentItem?.documentName}.pdf")

        val dm = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)

        val intentBroadCast = Intent(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intentBroadCast)
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
                    onLoading(false)
                    binding.etRevise.setText("")
                    UIKitPopUpFragment(this).showPopupSuccess()
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

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadCastDownloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadCastDownloadComplete)
    }

    private var broadCastDownloadComplete = object : BroadcastReceiver() {
        override fun onReceive(contex: Context?, p1: Intent?) {
            requireContext().openDownloadedPDF("${documentItem?.documentName}.pdf")
        }
    }


}