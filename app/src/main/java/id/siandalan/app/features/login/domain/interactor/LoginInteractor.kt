package id.siandalan.app.features.login.domain.interactor

import id.siandalan.app.features.login.domain.repository.LoginRepository
import id.siandalan.app.features.login.domain.state.LoginResultState
import id.siandalan.app.features.login.domain.usecase.LoginUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginInteractor(
    private val repository: LoginRepository,
    private val disposable: CompositeDisposable
): LoginUseCase {

    override fun login(
        username: String,
        password: String,
        callback: (data: LoginResultState) -> Unit
    ) {
        repository.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<LoginResultState> { LoginResultState.Success(it) }
            .onErrorReturn { LoginResultState.Error(it) }
            .startWithItem(LoginResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun clearDisposable() {
        disposable.clear()
    }
}