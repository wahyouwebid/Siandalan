package id.siandalan.app.features.home.domain.usecase

import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.home.domain.model.HomeItem

interface HomeUseCase {

    fun getDataHome(callback: (data: BaseResultState<HomeItem>) -> Unit)

    fun clearDisposable()
}