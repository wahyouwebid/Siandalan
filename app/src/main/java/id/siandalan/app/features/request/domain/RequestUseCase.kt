package id.siandalan.app.features.request.domain

import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.request.domain.model.RequestItem

interface RequestUseCase {

    fun getDataRequest(callback: (data: BaseResultState<RequestItem>) -> Unit)

    fun clearDisposable()
}