package id.siandalan.app.features.login.domain.interactor

import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.features.login.domain.model.ErrorLogin
import id.siandalan.app.features.login.domain.model.ModuleItem
import id.siandalan.app.features.login.domain.model.UserItem
import id.siandalan.app.features.login.domain.model.ValidationResult
import id.siandalan.app.features.login.domain.repository.LoginRepository
import id.siandalan.app.features.login.domain.usecase.LoginUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val repository: LoginRepository,
    private val disposable: CompositeDisposable
): LoginUseCase {

    override fun login(
        module: String?,
        username: String,
        password: String,
        callback: (data: BaseResultState<UserItem>) -> Unit
    ) {
        repository.login(module, username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<UserItem>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun getModule(callback: (data: BaseResultState<List<ModuleItem>>) -> Unit) {
        repository.getModule()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<BaseResultState<List<ModuleItem>>> { BaseResultState.Success(it) }
            .onErrorReturn { BaseResultState.Error(it) }
            .startWithItem(BaseResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun setUser(data: UserItem) {
        repository.setUser(data)
    }

    override fun setIsLogin(isLogin: Boolean) {
        repository.setIsLogin(isLogin)
    }

    override fun getTokenFirebase(): String {
        return repository.getTokenFirebase()
    }

    override fun setModuleName(module: String?) {
        repository.setModuleName(module)
    }

    override fun getModuleName(): String {
        return repository.getModuleName()
    }

    override fun getIsLogin(): Boolean {
        return repository.getIsLogin()
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

    override fun validateModule(module: String?): ValidationResult {
        return when {
            module.equals("Pilih Module")-> {
                ValidationResult(false, ErrorLogin.MODULE_IS_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun setIsRemember(isRemember: Boolean) {
        repository.setIsRemember(isRemember)
    }

    override fun getIsRemember(): Boolean {
        return repository.getIsRemember()
    }

    override fun setUsername(password: String?) {
        repository.setUsername(password)
    }

    override fun getUsername(): String {
        return repository.getUsername()
    }

    override fun setPassword(password: String?) {
        repository.setPassword(password)
    }

    override fun getPassword(): String {
        return repository.getPassword()
    }

    override fun clearDisposable() {
        disposable.clear()
    }
}