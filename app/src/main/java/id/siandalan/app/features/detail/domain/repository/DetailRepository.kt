package id.siandalan.app.features.detail.domain.repository

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody

interface DetailRepository {

    fun getDetail(id: String?): Observable<ResponseBody>

    fun getToken(): String

    fun getModule(): String

    fun getUsername(): String
}