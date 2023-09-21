package id.siandalan.app.features.request.data

import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.request.domain.model.RequestItem
import id.siandalan.app.features.request.domain.RequestRepository
import io.reactivex.rxjava3.core.Observable

class RequestDataRepository(
    private val apiService: RequestApiService,
    private val sessions: Sessions
): RequestRepository {

    override fun getDataRequest(): Observable<RequestItem> {
        val token = getToken()
        val module = getModule()
        val username = getUsername()
        return apiService.getRequestData(
            token, module, username
        ).map { response ->
            response.toDomain()
        }
    }

    override fun getToken() = sessions.getString(Sessions.accessToken)

    override fun getModule() = sessions.getString(Sessions.module)

    override fun getUsername() = sessions.getString(Sessions.username)

}