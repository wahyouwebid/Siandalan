package id.siandalan.app.features.login.data.api

import id.siandalan.app.features.login.data.model.DataLoginResponse
import id.siandalan.app.features.login.data.model.DataModuleResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginApiService {

    @FormUrlEncoded
    @POST("modules/{module}/public/Api/Android/API/user/login")
    fun login(
        @Path("module") module: String?,
        @Field("username") username: String,
        @Field("password") password: String,
    ): Observable<DataLoginResponse>

    @GET("public/Api/Version1/Module")
    fun getModule(): Observable<List<DataModuleResponse>>
}