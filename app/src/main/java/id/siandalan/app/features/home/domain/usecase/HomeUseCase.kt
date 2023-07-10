package id.siandalan.app.features.home.domain.usecase

import id.siandalan.app.features.home.domain.state.HomeResultState

interface HomeUseCase {

    fun getDataHome(callback: (data: HomeResultState) -> Unit)

    fun clearDisposable()
}