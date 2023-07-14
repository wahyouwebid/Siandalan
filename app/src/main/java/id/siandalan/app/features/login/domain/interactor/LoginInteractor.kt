package id.siandalan.app.features.login.domain.interactor

import id.siandalan.app.features.login.domain.model.ErrorLogin
import id.siandalan.app.features.login.domain.model.ValidationResult
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

    override fun getTokenFirebase(): String {
        return repository.getTokenFirebase()
    }

    override fun validateUserName(username: String): ValidationResult {
        return when {
            username.isEmpty() -> {
                ValidationResult(false, ErrorLogin.USERNAME_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validatePassword(password: String): ValidationResult {
        return when {
            password.isEmpty() -> {
                ValidationResult(false, ErrorLogin.PASSWORD_IS_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun clearDisposable() {
        disposable.clear()
    }
}