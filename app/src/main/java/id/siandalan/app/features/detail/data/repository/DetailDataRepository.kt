package id.siandalan.app.features.detail.data.repository

import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.detail.data.api.DetailApiService
import id.siandalan.app.features.detail.domain.repository.DetailRepository
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import javax.inject.Inject

class DetailDataRepository @Inject constructor(
    private val api: DetailApiService,
    private val sessions: Sessions
): DetailRepository {

    override fun getDetail(id: String?): Observable<ResponseBody> {
        val token = getToken()
        val module = getModule()
        val username = getUsername()
        return api.getDetail(token, module, username, id)
    }

    override fun getToken() = sessions.getString(Sessions.accessToken)

    override fun getModule() = sessions.getString(Sessions.module)

    override fun getUsername() = sessions.getString(Sessions.username)
}