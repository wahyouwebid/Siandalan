package id.siandalan.app.features.pdfview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.BuildConfig
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseActivity
import id.siandalan.app.databinding.ActivityPdfViewBinding

@AndroidEntryPoint
class PdfViewActivity : BaseActivity<ActivityPdfViewBinding>(ActivityPdfViewBinding::inflate) {

    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        val pdf = intent?.getStringExtra("url")
        val urlPdf = "${BuildConfig.baseUrl}/modules/pusat/public/${pdf}"
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$urlPdf")

        // Set a WebViewClient to handle page navigation
        webView.webViewClient = WebViewClient()
    }

    override fun setupViewModel() {

    }
}