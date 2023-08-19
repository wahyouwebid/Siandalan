package id.siandalan.app.features.detail.presentation.ui

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.common.base.BaseFragment
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.FragmentDetailBinding
import id.siandalan.app.features.detail.presentation.viewmodel.DetailViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException


@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val url = "http://116.0.4.24:808/siandalan-pengembangan/modules/kabupaten-tangerang/public/Api/Android/Api_andalalin/detail"

    private val dataId: String? by lazy {
        arguments?.getString("id")
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        val okHttpClient = OkHttpClient.Builder().build()
//        val webViewClient = MyWebViewClient(viewModel.getToken(), okHttpClient)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("username", "333333333555555555")
            .addFormDataPart("id", "4")
            .build()

        val request: Request = Request.Builder()
            .url(url)
            .addHeader("Authorization", viewModel.getToken())
            .addHeader("Content-Type", "multipart/form-data")
            .post(requestBody)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("log", e.message.toString())
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val htmlString: String = response.body?.string() ?: ""
                webView.post {
                    webView.clearCache(true)
                    webView.loadData(htmlString, "text/html", "UTF-8")
                }
            }

        })
        //webView.loadUrl(url, getHeaders())
    }

    override fun setupViewModel() {
//        viewModel.getDetail(dataId)
//        viewModel.detail.observe(viewLifecycleOwner) { state ->
//            when(state) {
//                is BaseResultState.Success -> setDataDetail(state.data)
//                is BaseResultState.Loading -> {}
//                is BaseResultState.Error -> onError(state.error)
//            }
//        }
    }

    private fun setDataDetail(response: ResponseBody) {
        val data = response.string()
        Log.d("WEB NIH", data)
        //binding.tvName.text = Html.fromHtml(data)
        with(binding) {
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadData(data, "text/html", "UTF-8")
        }
    }

    private fun postURL(url: String) = with(binding) {

        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("username", "333333333555555555")
            .addFormDataPart("id", "4")
            .build()

        val request: Request = Request.Builder()
            .url(url)
            .addHeader("Authorization", viewModel.getToken())
            .addHeader("Content-Type", "multipart/form-data")
            .post(requestBody)
            .build()
        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("log", e.message.toString())
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val htmlString: String = response.body?.string() ?: ""
                webView.post {
                    webView.clearCache(true)
                    webView.loadDataWithBaseURL(url, htmlString, "text/html", "utf-8", null)
                }
            }

        })
    }

    private fun setWebView() = with(binding){
        webView.webViewClient = object : WebViewClient() {
            // Handle API until level 21
            @Suppress("deprecation")
            override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                return getNewResponse(url)
            }

            // Handle API 21+
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                val url = request.url.toString()
                return getNewResponse(url)
            }

            private fun getNewResponse(url: String): WebResourceResponse? {
                return try {
                    val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("username", "333333333555555555")
                        .addFormDataPart("id", "4")
                        .build()

                    val httpClient = OkHttpClient()
                    val request: Request = Request.Builder()
                        .url(url.trim { it <= ' ' })
                        .addHeader("Authorization", viewModel.getToken()) // Example header
                        .post(requestBody)
                        .build()
                    val response: Response = httpClient.newCall(request).execute()

                    WebResourceResponse(
                        url,
                        response.header("content-encoding", "utf-8"),
                        response.body?.byteStream()
                    )
                } catch (e: Exception) {
                    null
                }
            }
        }
        webView.loadUrl(url)
    }

    private fun onError(error: Throwable) = with(binding){
        uikitError.show()
        uikitError.setError(error) {
            viewModel.getDetail(dataId)
        }
    }

    private fun getHeaders(): Map<String?, String?> {
        val extraHeaders: MutableMap<String, String> = HashMap()
        extraHeaders["Authorization"] = viewModel.getToken()
        return extraHeaders.toMutableMap()
    }
}