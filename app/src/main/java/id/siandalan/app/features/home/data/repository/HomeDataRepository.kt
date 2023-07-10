package id.siandalan.app.features.home.data.repository

import id.siandalan.app.features.home.data.api.ApiService
import id.siandalan.app.features.home.domain.model.DataHome
import id.siandalan.app.features.home.domain.repository.HomeRepository
import io.reactivex.rxjava3.core.Observable

class HomeDataRepository(
    private val apiService: ApiService
): HomeRepository {

    override fun getDataHome(): Observable<DataHome> {
        return apiService.getHomeData().map { response ->
            response.toDomain()
        }
    }

}