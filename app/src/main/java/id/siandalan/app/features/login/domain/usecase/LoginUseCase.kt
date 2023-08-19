package id.siandalan.app.features.login.domain.usecase

import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.login.domain.model.ModuleItem
import id.siandalan.app.features.login.domain.model.UserItem
import id.siandalan.app.features.login.domain.model.ValidationResult

interface LoginUseCase {

    fun login(
        module: String?,
        username: String,
        password: String,
        callback: (data: BaseResultState<UserItem>) -> Unit
    )

    fun getModule(
        callback: (data: BaseResultState<List<ModuleItem>>) -> Unit
    )

    fun setUser(data: UserItem)

    fun setIsLogin(isLogin: Boolean)

    fun getTokenFirebase(): String

    fun setModuleName(module: String?)

    fun getModuleName(): String

    fun getIsLogin(): Boolean

    fun validateUserName(username: String): ValidationResult

    fun validatePassword(password: String): ValidationResult

    fun validateModule(module: String?): ValidationResult

    fun clearDisposable()
}