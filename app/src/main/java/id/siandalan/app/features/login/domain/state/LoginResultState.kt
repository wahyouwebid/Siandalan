package id.siandalan.app.features.login.domain.state

import id.siandalan.app.features.login.domain.model.LoginItem

sealed class LoginResultState{

    data class Success(val data: LoginItem) : LoginResultState()

    data class Error(val error: Throwable) : LoginResultState()

    object Loading : LoginResultState()

}