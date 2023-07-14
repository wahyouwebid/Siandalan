package id.siandalan.app.features.request.data.repository

import id.siandalan.app.features.request.data.api.RequestApiService
import id.siandalan.app.features.request.domain.model.RequestItem
import id.siandalan.app.features.request.domain.repository.RequestRepository
import io.reactivex.rxjava3.core.Observable

class RequestDataRepository(
    private val apiService: RequestApiService
): RequestRepository {

    override fun getDataRequest(): Observable<RequestItem> {
        return apiService.getRequestData().map { response ->
            response.toDomain()
        }
    }

}