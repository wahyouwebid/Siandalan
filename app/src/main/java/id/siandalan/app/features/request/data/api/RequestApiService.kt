package id.siandalan.app.features.request.data.api

import id.siandalan.app.features.request.data.model.DataRequestResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface RequestApiService {

    @GET("siandalan-pengembangan/modules/bptj/public/api/Version1/Api_andalalin")
    fun getRequestData(): Observable<DataRequestResponse>

}