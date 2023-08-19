package id.siandalan.app.features.detail.domain.usecase

import id.siandalan.app.common.base.BaseResultState
import okhttp3.ResponseBody

interface DetailUseCase {

    fun getDetail(
        id: String?,
        callback: (BaseResultState<ResponseBody>) -> Unit
    )

    fun getToken(): String

    fun clearDisposable()
}