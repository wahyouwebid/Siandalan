package id.siandalan.app.features.login.data.api

import com.google.gson.JsonObject
import id.siandalan.app.features.login.data.model.DataLoginResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("/login")
    fun login(
        @Body request: JsonObject
    ): Observable<DataLoginResponse>

}