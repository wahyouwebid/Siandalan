package id.siandalan.app.features.request.domain.usecase

import id.siandalan.app.features.request.domain.state.RequestResultState

interface RequestUseCase {

    fun getDataRequest(callback: (data: RequestResultState) -> Unit)

    fun clearDisposable()
}