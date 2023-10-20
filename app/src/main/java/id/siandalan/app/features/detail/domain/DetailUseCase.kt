package id.siandalan.app.features.detail.domain

import id.siandalan.app.common.base.BaseResultState
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody

interface DetailUseCase {

    fun getDetail(
        id: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    )

    fun postRevise(
        id: String?,
        catatanDraftSk: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    )

    fun postTtd(
        id: String?,
        passphrase: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    )

    fun postApprove(
        id: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    )

    fun getModule(): String

    fun clearDisposable()
}