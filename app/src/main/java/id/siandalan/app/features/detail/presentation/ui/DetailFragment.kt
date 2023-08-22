package id.siandalan.app.features.detail.presentation.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.FragmentDetailBinding
import id.siandalan.app.features.detail.presentation.viewmodel.DetailViewModel
import okhttp3.ResponseBody


@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val dataId: String? by lazy {
        arguments?.getString("id")
    }

    override fun setupView(savedInstanceState: Bundle?) {}

    override fun setupViewModel() {
        viewModel.getDetail(dataId)
        viewModel.detail.observe(viewLifecycleOwner) { state ->
            when(state) {
                is BaseResultState.Success -> setDataDetail(state.data)
                is BaseResultState.Loading -> {}
                is BaseResultState.Error -> onError(state.error)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setDataDetail(response: ResponseBody) = with(binding) {
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java", ReplaceWith("true"))
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                uikitLoading.setLoadingProgress(true)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                uikitLoading.setLoadingProgress(false)
                super.onPageFinished(view, url)
            }
        }
        val content = response.string()
        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null)
    }


    private fun onError(error: Throwable) = with(binding){
        uikitError.show()
        uikitError.setError(error) {
            viewModel.getDetail(dataId)
        }
    }

}