package id.siandalan.app.features.home.domain.state

import id.siandalan.app.features.home.domain.model.DataHome

sealed class HomeResultState{

    data class Success(val data: DataHome) : HomeResultState()

    data class Error(val error: Throwable) : HomeResultState()

    object Loading : HomeResultState()

}