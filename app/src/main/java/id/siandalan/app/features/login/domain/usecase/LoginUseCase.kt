package id.siandalan.app.features.login.domain.usecase

import id.siandalan.app.features.login.domain.state.LoginResultState

interface LoginUseCase {

    fun login(
        username: String,
        password: String,
        callback: (data: LoginResultState) -> Unit
    )

    fun clearDisposable()
}