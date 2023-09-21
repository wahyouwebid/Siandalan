package id.siandalan.app.features.login.domain

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

    fun setIsRemember(isRemember: Boolean)

    fun getIsRemember(): Boolean

    fun setUsername(password: String?)

    fun getUsername(): String

    fun setPassword(password: String?)

    fun getPassword(): String

}