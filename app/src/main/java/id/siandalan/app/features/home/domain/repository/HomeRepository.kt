package id.siandalan.app.features.home.domain.repository

import id.siandalan.app.features.home.domain.model.DataHome
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {

    fun getDataHome(): Observable<DataHome>

}