package id.siandalan.app.features.home.data.repository

import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.home.data.api.HomeApiService
import id.siandalan.app.features.home.domain.model.HomeItem
import id.siandalan.app.features.home.domain.repository.HomeRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HomeDataRepository @Inject constructor(
    private val apiService: HomeApiService,
    private val sessions: Sessions
): HomeRepository {

    override fun getDataHome(): Observable<HomeItem> {
        val token = getToken()
        val module = getModule()
        val username = getUsername()
        return apiService.getHomeData(
            token,
            module,
            username
        ).map { response -> response.toDomain() }
    }

    override fun getToken() = sessions.getString(Sessions.accessToken)

    override fun getModule() = sessions.getString(Sessions.module)

    override fun getUsername() = sessions.getString(Sessions.username)
}