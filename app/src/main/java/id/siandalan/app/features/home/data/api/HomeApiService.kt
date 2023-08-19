package id.siandalan.app.features.home.data.api

import id.siandalan.app.features.home.data.model.DataHomeResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApiService {

    @FormUrlEncoded
    @POST("modules/{module}/public/Api/Android/Api_andalalin")
    fun getHomeData(
        @Header("Authorization") token: String,
        @Path("module") module: String,
        @Field("username") username: String
    ): Observable<DataHomeResponse>

}