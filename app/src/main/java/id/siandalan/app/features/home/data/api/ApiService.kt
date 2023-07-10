package id.siandalan.app.features.home.data.api

import id.siandalan.app.features.home.data.model.DataHomeResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("siandalan-pengembangan/modules/pusat/public/api/Version1/Api_andalalin")
    fun getHomeData(): Observable<DataHomeResponse>

}