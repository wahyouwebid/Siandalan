package id.siandalan.app.features.login.domain.repository

import id.siandalan.app.features.login.domain.model.ModuleItem
import id.siandalan.app.features.login.domain.model.UserItem
import io.reactivex.rxjava3.core.Observable

interface LoginRepository {

    fun login(
        module: String?,
        username: String,
        password: String
    ): Observable<UserItem>

    fun getModule(): Observable<List<ModuleItem>>

    fun setUser(data: UserItem)

    fun setIsLogin(isLogin: Boolean)

    fun getTokenFirebase(): String

    fun setModuleName(module: String?)

    fun getModuleName(): String

    fun getIsLogin(): Boolean

}