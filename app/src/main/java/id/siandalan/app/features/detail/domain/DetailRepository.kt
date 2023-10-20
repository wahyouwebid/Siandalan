package id.siandalan.app.features.detail.domain

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody

interface DetailRepository {

    fun getDetail(id: String?): Observable<ResponseBody>

    fun postRevise(
        id: String?,
        catatanDraftSk: String?,
    ): Observable<ResponseBody>

    fun postTtd(
        id: String?,
        passphrase: String?,
    ): Observable<ResponseBody>

    fun postApprove(
        id: String?,
    ): Observable<ResponseBody>

    fun getToken(): String

    fun getModule(): String

    fun getUsername(): String
}