package id.siandalan.app.features.login.domain.usecase

import id.siandalan.app.features.login.domain.model.ValidationResult
import id.siandalan.app.features.login.domain.state.LoginResultState

interface LoginUseCase {

    fun login(
        username: String,
        password: String,
        callback: (data: LoginResultState) -> Unit
    )

    fun validateUserName(username: String): ValidationResult
    fun validatePassword(password: String): ValidationResult

    fun clearDisposable()
}