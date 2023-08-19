package id.siandalan.app.features.detail.data.api

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface DetailApiService {

    @FormUrlEncoded
    @Headers("Accept: text/html")
    @POST("modules/{module}/public/Api/Android/Api_andalalin/detail")
    fun getDetail(
        @Header("Authorization") token: String,
        @Path("module") module: String,
        @Field("username") username: String,
        @Field("id") id: String?
    ): Observable<ResponseBody>

}