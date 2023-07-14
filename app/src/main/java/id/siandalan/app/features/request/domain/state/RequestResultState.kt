package id.siandalan.app.features.request.domain.state

import id.siandalan.app.features.request.domain.model.RequestItem

sealed class RequestResultState{

    data class Success(val data: RequestItem) : RequestResultState()

    data class Error(val error: Throwable) : RequestResultState()

    object Loading : RequestResultState()

}