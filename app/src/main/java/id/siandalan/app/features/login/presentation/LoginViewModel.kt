package id.siandalan.app.features.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.features.login.domain.state.LoginResultState
import id.siandalan.app.features.login.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
) : ViewModel() {

    val login: MutableLiveData<LoginResultState> by lazy {
        MutableLiveData()
    }

    fun login(username: String, password: String) {
        useCase.login(username, password) {
            login.postValue(it)
        }
    }
}