package id.siandalan.app.features.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import id.siandalan.app.BuildConfig
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.databinding.FragmentDetailBinding

class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupView(savedInstanceState: Bundle?) {
        with(binding) {
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl(BuildConfig.webUrl)
        }
    }

    override fun setupViewModel() {

    }
}