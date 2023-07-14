package id.siandalan.app.features.login.domain.repository

import id.siandalan.app.features.login.domain.model.LoginItem
import io.reactivex.rxjava3.core.Observable

interface LoginRepository {

    fun login(username: String, password: String): Observable<LoginItem>

    fun getTokenFirebase(): String

}