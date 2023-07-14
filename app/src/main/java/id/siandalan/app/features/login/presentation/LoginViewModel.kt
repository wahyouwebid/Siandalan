package id.siandalan.app.features.login.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.common.firebase.FirebaseUtils
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.ActivityLoginBinding
import id.siandalan.app.features.login.domain.model.ValidationResult
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

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun login(
        binding: ActivityLoginBinding,
        username: String,
        password: String
    ) {
        val tokenFirebase = useCase.getTokenFirebase()
        val validateUsername = validateUsername(binding, username)
        val validatePassword = validatePassword(binding, password)

        val hasError = listOf(
            validateUsername,
            validatePassword,
        )

        if (!hasError.any { !it.successful }) {
            useCase.login(username, password) {
                login.postValue(it)
            }
        } else {
            hasError.reversed().filter { !it.successful }.map {
                errorMessage.postValue(it.errorMessage)
            }
        }
    }

    fun validateUsername(
        binding: ActivityLoginBinding,
        username: String
    ): ValidationResult = with(binding){
        val result = useCase.validateUserName(username)
        if (result.errorMessage?.isNotEmpty() == true) {
            tvErrorUsername.show()
            tvErrorUsername.text = result.errorMessage
        } else {
            tvErrorUsername.hide()
        }
        return result
    }

    fun validatePassword(
        binding: ActivityLoginBinding,
        username: String
    ): ValidationResult = with(binding) {
        val result = useCase.validatePassword(username)
        if (result.errorMessage?.isNotEmpty() == true) {
            tvErrorPassword.show()
            tvErrorPassword.text = result.errorMessage
        } else {
            tvErrorPassword.hide()
        }
        return result
    }
}