package id.siandalan.app.core.weview


/**
 * Created by wahyouwebid on 18 August 2023
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */
//class CustomWebViewClient(private val authToken: String) : WebViewClient() {
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun shouldInterceptRequest(
//        view: WebView?,
//        request: WebResourceRequest
//    ): WebResourceResponse? {
//        val url = request.url.toString()
//
//        if (url.contains("your_api_endpoint_here")) { // Modify this condition to match your API endpoint
//            val headers = HashMap<String, String>()
//            headers["Authorization"] = "Bearer $authToken"
//
//            // Modify form data if needed
//            val modifiedRequest = modifyRequest(request, headers)
//
//            return super.shouldInterceptRequest(view, modifiedRequest)
//        }
//
//        return super.shouldInterceptRequest(view, request)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    private fun modifyRequest(
//        request: WebResourceRequest,
//        headers: Map<String, String>
//    ): WebResourceRequest {
//        val modifiedHeaders = HashMap<String, String>()
//        modifiedHeaders.putAll(request.requestHeaders)
//        modifiedHeaders.putAll(headers)
//
//        val modifiedRequestBuilder = WebResourceRequest(request.url)
//            .setMethod(request.method, request.requestHeaders)
//            .setRequestHeaders(modifiedHeaders)
//
//        if (request.method?.equals("POST", true) == true) {
//            // Modify form data here if needed
//            val modifiedPostData = "key=value&key2=value2" // Modify this to your form data
//            val postDataByteArray = modifiedPostData.toByteArray()
//            modifiedRequestBuilder.setPostData("application/x-www-form-urlencoded", postDataByteArray)
//        }
//
//        return modifiedRequestBuilder.build()
//    }
//}


import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MyWebViewClient(
    private val token: String,
    private val okHttpClient: OkHttpClient
) : WebViewClient() {

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("username", "333333333555555555")
            .addFormDataPart("id", "4")
            .build()

        val okHttpRequest = Request.Builder()
            .url(request?.url?.toString() ?: "")
            .addHeader("Authorization", token)
            .post(requestBody)
            .build()

        try {
            val response = okHttpClient.newCall(okHttpRequest).execute()
            val contentType = response.header("content-type")
            val charset = contentType?.substringAfter("charset=")?.toUpperCase() ?: "UTF-8"

            return WebResourceResponse(contentType, charset, response.body?.byteStream())
        } catch (e: IOException) {
            // Handle exceptions
        }

        return super.shouldInterceptRequest(view, request)
    }
}