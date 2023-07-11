package id.siandalan.app.features.home.data.repository

import id.siandalan.app.features.home.data.api.HomeApiService
import id.siandalan.app.features.home.domain.model.HomeItem
import id.siandalan.app.features.home.domain.repository.HomeRepository
import io.reactivex.rxjava3.core.Observable

class HomeDataRepository(
    private val apiService: HomeApiService
): HomeRepository {

    override fun getDataHome(): Observable<HomeItem> {
        return apiService.getHomeData().map { response ->
            response.toDomain()
        }
    }

}