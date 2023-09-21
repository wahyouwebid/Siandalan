package id.siandalan.app.features.login.data

import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.login.domain.model.ModuleItem
import id.siandalan.app.features.login.domain.model.UserItem
import id.siandalan.app.features.login.domain.LoginRepository
import io.reactivex.rxjava3.core.Observable

class LoginDataRepository(
    private val api: LoginApiService,
    private val sessions: Sessions
): LoginRepository {

    override fun login(
        module: String?,
        username: String,
        password: String
    ): Observable<UserItem> {
        return api.login(module, username, password).map { it.user.toDomain(it.accessToken) }
    }

    override fun getModule(): Observable<List<ModuleItem>> {
        return api.getModule().map { it.map { it.toDomain() } }
    }

    override fun setUser(data: UserItem) {
        sessions.apply {
            putString(Sessions.id, data.id)
            putString(Sessions.idRole, data.idRole)
            putString(Sessions.username, data.username)
            putString(Sessions.name, data.name)
            putString(Sessions.aksesProvinsi, data.aksesProvinsi)
            putString(Sessions.aksesKota, data.aksesKota)
            putString(Sessions.position, data.position)
            putString(Sessions.rank, data.rank)
            putString(Sessions.nik, data.nik)
            putString(Sessions.nip, data.nip)
            putString(Sessions.accessToken, "Bearer " + data.accessToken)
            putBoolean(Sessions.isLogin, true)
        }
    }

    override fun setIsLogin(isLogin: Boolean) {
        sessions.putBoolean(Sessions.isLogin, isLogin)
    }

    override fun getTokenFirebase(): String {
        return sessions.getString(Sessions.tokenFirebase)
    }

    override fun setModuleName(module: String?) {
        sessions.putString(Sessions.module, module)
    }

    override fun getModuleName(): String {
        return sessions.getString(Sessions.module)
    }

    override fun getIsLogin(): Boolean {
        return sessions.getBoolean(Sessions.isLogin)
    }

    override fun setIsRemember(isRemember: Boolean) {
        sessions.putBoolean(Sessions.isRemember, isRemember)
    }

    override fun getIsRemember(): Boolean {
        return sessions.getBoolean(Sessions.isRemember)
    }

    override fun setUsername(password: String?) {
        sessions.putString(Sessions.username, password)
    }

    override fun getUsername(): String {
        return sessions.getString(Sessions.username)
    }

    override fun setPassword(password: String?) {
        sessions.putString(Sessions.password, password)
    }

    override fun getPassword(): String {
        return sessions.getString(Sessions.password)
    }

}