package id.siandalan.app.features.request.domain.repository

import id.siandalan.app.features.request.domain.model.RequestItem
import io.reactivex.rxjava3.core.Observable

interface RequestRepository {

    fun getDataRequest(): Observable<RequestItem>

}