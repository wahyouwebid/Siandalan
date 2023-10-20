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
import id.siandalan.app.common.utils.checkEmpty
import id.siandalan.app.common.utils.dateFormat
import id.siandalan.app.common.utils.dateFormatCompleteWithSecond
import id.siandalan.app.common.utils.formatCurrency
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.openDownloadedPDF
import id.siandalan.app.common.utils.parcelable
import id.siandalan.app.common.utils.parcelableArrayList
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

    private val dataDocument: List<HomeItem.DocumentItem>? by lazy {
        arguments?.parcelableArrayList(Constant.DataParcelize.DOCUMENT.name)
    }

    private val dataRole: String? by lazy {
        arguments?.getString(Constant.DataParcelize.ROLE.name)
    }

    private var documentItem: HomeItem.DocumentItem? = null

    private var fileDocumentSk: String? = null

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

        homeAdapter.setData(dataDocument)
        homeAdapter.notifyItemRangeChanged(0, homeAdapter.itemCount)

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

        if (dataRole == Constant.Role.KASUBDIT.name) {
            btnRevise.setOnClickListener {
                if (!etRevise.text.isNullOrEmpty()) {
                    viewModel.postRevise(data?.id, etRevise.text.toString())
                } else {
                    Toast.makeText(requireContext(), "Form revisi tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }

            btnApprove.setOnClickListener {
                viewModel.postApprove(data?.id)
            }
        } else {
            btnRevise.setOnClickListener {
                if (!etRevise.text.isNullOrEmpty()) {
                    viewModel.postRevise(data?.id, etRevise.text.toString())
                } else {
                    Toast.makeText(requireContext(), "Form revisi tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }

            tvEtTtdTanganLabel.show()
            etTtdTangan.show()

            btnApprove.text = getString(R.string.title_tanda_tangan)
            btnApprove.setOnClickListener {
                if (!etTtdTangan.text.isNullOrEmpty()) {
                    viewModel.postTtd(data?.id, etTtdTangan.text.toString())
                } else {
                    Toast.makeText(requireContext(), "Form BSRE Passphrase tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        pdfSkAndalan.setOnClickListener {

        }
    }

    private fun setDataPengajuan() = with(binding){
        tvPengajuanNib.text = data?.nib.checkEmpty()
        tvPengajuanName.text = data?.name.checkEmpty()
        tvPengajuanEmail.text = data?.emailPerusahaan.checkEmpty()
        tvPengajuanPhone.text = data?.nomorTelponPerseroan.checkEmpty()
        tvPengajuanLeaderName.text = data?.leaderName.checkEmpty()
        tvPengajuanPosition.text = data?.leaderPosition.checkEmpty()

        tvPengajuanCategory.text = data?.category.checkEmpty()
        tvPengajuanSubCategory.text = data?.subCategory.checkEmpty()
        tvPengajuanUkuran.text = data?.capacity.checkEmpty()
        tvPengajuanKlasifikasi.text = data?.classification.checkEmpty()
        tvPengajuanKapasitasRill.text = data?.kapasitasRiil.checkEmpty()
        tvPengajuanNamaProyek.text = data?.projectName.checkEmpty()
        tvPengajuanPembangunan.text = data?.pembangunan.checkEmpty()
        tvPengajuanAlamatProyek.text = data?.projectAddress.checkEmpty()
        tvPengajuanProvinsi.text = data?.projectProvince.checkEmpty()
        tvPengajuanKota.text = data?.projectRegency.checkEmpty()
        tvPengajuanNoSuratPermohonan.text = data?.applicationLetterNo.checkEmpty()
        tvPengajuanTglSuratPermohonan.text = data?.applicationLetterDate?.dateFormat().checkEmpty()
    }

    private fun setPembayaranPnbp() = with(binding) {
        tvPembayaranNoAndalin.text = data?.noAndalalin.checkEmpty()
        tvPembayaranHarga.text = if (data?.price != null) {
            data?.price?.toDouble()?.formatCurrency()?.replace("Rp", "Rp. ")
        } else {
            "-"
        }
        tvKodeBilling.text = data?.pengajuanKodeBilling.checkEmpty()
        tvKodeBilling.text = data?.billingCode.checkEmpty()
        tvTanggalPembayaran.text = data?.paymentDate?.dateFormatCompleteWithSecond().checkEmpty()
        tvKodeBilling.text = data?.billingCode.checkEmpty()

        val urlInvoice = "${BuildConfig.baseUrl}modules/${getModule()}/public/${data?.draftFinalLetterFile}"
        val urlKuitansi = "${BuildConfig.baseUrl}modules/${getModule()}/public/${data?.draftFinalLetterFile}"
//        setupPdf(urlInvoice, pdfPembayaranInvoice)
//        setupPdf(urlKuitansi, pdfPembayaranKuitansi)
    }

    private fun setSkAndalan()= with(binding){
        val urlSk = "${BuildConfig.baseUrl}modules/${getModule()}/public/${data?.draftFinalLetterFile}"
        val fileName = data?.draftFinalLetterFile?.replace("uploads/andalalin/", "")
        fileDocumentSk = fileName
        setupPdf(urlSk, pdfSkAndalan)
        pdfSkAndalan.setOnClickListener {
            val request = DownloadManager.Request(Uri.parse(urlSk))
                .setTitle("$fileName")
                .setDescription("Downloading $fileName")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${fileName}.pdf")

            val dm = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)

            val intentBroadCast = Intent(DownloadManager.ACTION_VIEW_DOWNLOADS)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intentBroadCast)
        }
    }

    private fun setupPdf(url: String, pdfView: PDFView) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val input = URL(url).openStream()
                pdfView.fromStream(input)
                    .swipeHorizontal(false)
                    .enableSwipe(false)
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
        val url = documentItem?.fileName
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("${documentItem?.name}")
            .setDescription("Downloading ${documentItem?.name}")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${documentItem?.name}.pdf")

        val dm = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)

        val intentBroadCast = Intent(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intentBroadCast)
    }

    override fun setupViewModel() {
        setDataPengajuan()
        setPembayaranPnbp()
        setSkAndalan()

        viewModel.revise.observe(viewLifecycleOwner) {
            when(it) {
                is BaseResultState.Loading -> onLoading(true)
                is BaseResultState.Success -> {
                    onLoading(false)
                    binding.etRevise.setText("")
                    UIKitPopUpFragment(this).showPopupSuccess(
                        getString(R.string.title_revise_success)
                    )
                }
                is BaseResultState.Error -> onErrorRevise()
            }
        }

        viewModel.ttd.observe(viewLifecycleOwner) {
            when(it) {
                is BaseResultState.Loading -> onLoading(true)
                is BaseResultState.Success -> {
                    onLoading(false)
                    binding.etTtdTangan.setText("")
                    UIKitPopUpFragment(this).showPopupSuccess(
                        getString(R.string.title_ttd_success)
                    )
                }
                is BaseResultState.Error -> onErrorRevise()
            }
        }

        viewModel.approve.observe(viewLifecycleOwner) {
            when(it) {
                is BaseResultState.Loading -> onLoading(true)
                is BaseResultState.Success -> {
                    onLoading(false)
                    binding.etRevise.setText("")
                    UIKitPopUpFragment(this).showPopupSuccess(
                        getString(R.string.title_approve_success)
                    )
                }
                is BaseResultState.Error -> onErrorApprove()
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


    private fun onErrorRevise() = with(binding){
        uikitLoading.hide()
        UIKitPopUpFragment(this@DetailFragment).showPopupError {
            viewModel.postRevise(data?.id, etRevise.text.toString())
        }
    }

    private fun onErrorApprove() = with(binding){
        uikitLoading.hide()
        UIKitPopUpFragment(this@DetailFragment).showPopupError {
            viewModel.postApprove(data?.id)
        }
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadCastDownloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadCastDownloadDocSkComplete,
            IntentFilter(DownloadManager.ACTION_VIEW_DOWNLOADS)
        )
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadCastDownloadComplete)
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadCastDownloadDocSkComplete)
    }

    private var broadCastDownloadComplete = object : BroadcastReceiver() {
        override fun onReceive(contex: Context?, p1: Intent?) {
            requireContext().openDownloadedPDF("${documentItem?.name}.pdf")
        }
    }

    private var broadCastDownloadDocSkComplete = object : BroadcastReceiver() {
        override fun onReceive(contex: Context?, p1: Intent?) {
            requireContext().openDownloadedPDF("${fileDocumentSk}.pdf")
        }
    }

    private fun getModule(): String {
        return viewModel.getModule()
    }

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


}