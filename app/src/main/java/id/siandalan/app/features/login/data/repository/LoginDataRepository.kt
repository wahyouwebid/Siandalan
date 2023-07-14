package id.siandalan.app.features.login.data.repository

import com.google.gson.JsonParser
import id.siandalan.app.common.sessions.Sessions
import id.siandalan.app.features.login.data.api.LoginApiService
import id.siandalan.app.features.login.domain.model.LoginItem
import id.siandalan.app.features.login.domain.repository.LoginRepository
import io.reactivex.rxjava3.core.Observable
import org.json.JSONObject

class LoginDataRepository(
    private val apiService: LoginApiService,
    private val sessions: Sessions
): LoginRepository {

    override fun login(username: String, password: String): Observable<LoginItem> {
        val json = JSONObject().also {
            it.put("username", username)
            it.put("password", password)
        }
        val request = JsonParser.parseString(json.toString()).asJsonObject
        return apiService.login(request).map { it.toDomain() }
    }

    override fun getTokenFirebase(): String {
        return sessions.getString(Sessions.tokenFirebase)
    }

}