package id.siandalan.app.features.home.domain

import id.siandalan.app.features.home.domain.model.HomeItem
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {

    fun getDataHome(): Observable<HomeItem>

    fun deviceUpdate(): Observable<Any>

    fun getToken(): String

    fun getModule(): String

    fun getUsername(): String

    fun getFirebaseToken(): String
}