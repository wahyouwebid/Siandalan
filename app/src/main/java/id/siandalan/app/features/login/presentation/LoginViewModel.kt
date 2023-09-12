package id.siandalan.app.features.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.ActivityLoginBinding
import id.siandalan.app.features.login.domain.model.ModuleItem
import id.siandalan.app.features.login.domain.model.UserItem
import id.siandalan.app.features.login.domain.model.ValidationResult
import id.siandalan.app.features.login.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
) : ViewModel() {

    val login: MutableLiveData<BaseResultState<UserItem>> by lazy {
        MutableLiveData()
    }

    val module: MutableLiveData<BaseResultState<List<ModuleItem>>> by lazy {
        MutableLiveData()
    }

    val moduleList: MutableLiveData<List<ModuleItem>> by lazy {
        MutableLiveData()
    }

    val moduleSelected: MutableLiveData<ModuleItem> by lazy {
        MutableLiveData()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun login(
        binding: ActivityLoginBinding,
        isShownModule: Boolean,
        module: String?,
        username: String,
        password: String,
        isRemember: Boolean
    ) {
        val validateUsername = validateUsername(binding, username)
        val validatePassword = validatePassword(binding, password)
        val validateModule = if (isShownModule) {
            validateModule(binding, binding.tvModule.text.toString())
        } else {
            ValidationResult(true, "")
        }

        val hasError = listOf(
            validateUsername,
            validatePassword,
            validateModule
        )

        if (!hasError.any { !it.successful }) {
            useCase.login(module, username, password) {
                login.postValue(it)
                useCase.setIsRemember(isRemember)
                if (isRemember) {
                    useCase.setUsername(username)
                    useCase.setPassword(password)
                }
            }
        } else {
            hasError.reversed().filter { !it.successful }.map {
                errorMessage.postValue(it.errorMessage)
            }
        }
    }

    fun getModule() {
        useCase.getModule {
            module.postValue(it)
        }
    }

    fun setModuleList(data: List<ModuleItem>) {
        moduleList.postValue(data)
    }

    fun setUser(data: UserItem) {
        useCase.setUser(data)
    }

    fun setIsLogin(isLogin: Boolean) {
        useCase.setIsLogin(isLogin)
    }

    fun setModuleName(module: String?) {
        useCase.setModuleName(module)
    }

    fun getModuleName(): String? {
        return useCase.getModuleName()
    }

    fun getIsLogin(): Boolean {
        return useCase.getIsLogin()
    }

    fun getIsRemember(): Boolean {
        return useCase.getIsRemember()
    }

    fun getUsername(): String {
        return useCase.getUsername()
    }

    fun getPassword(): String {
        return useCase.getPassword()
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
        password: String
    ): ValidationResult = with(binding) {
        val result = useCase.validatePassword(password)
        if (result.errorMessage?.isNotEmpty() == true) {
            tvErrorPassword.show()
            tvErrorPassword.text = result.errorMessage
        } else {
            tvErrorPassword.hide()
        }
        return result
    }

    fun validateModule(
        binding: ActivityLoginBinding,
        module: String?
    ): ValidationResult = with(binding) {
        val result = useCase.validateModule(module)
        if (result.errorMessage?.isNotEmpty() == true) {
            tvErrorModule.show()
            tvErrorModule.text = result.errorMessage
        } else {
            tvErrorModule.hide()
        }
        return result
    }
}