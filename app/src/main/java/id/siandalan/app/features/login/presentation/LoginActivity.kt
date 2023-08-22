package id.siandalan.app.features.login.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseActivity
import id.siandalan.app.common.base.BaseResultState
import id.siandalan.app.common.utils.convertToCamelCase
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.core.firebase.FirebaseUtils
import id.siandalan.app.core.mapper.ErrorMapper
import id.siandalan.app.databinding.ActivityLoginBinding
import id.siandalan.app.features.login.domain.model.ModuleItem
import id.siandalan.app.features.login.domain.model.UserItem
import id.siandalan.app.features.main.MainActivity
import retrofit2.HttpException
import java.io.IOException

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    private var module: String? = ""
    private var moduleIsShown: Boolean = true

    override fun setupView(savedInstanceState: Bundle?) = with(binding){
        FirebaseUtils.generateTokenFirebase().toString()

        etUsername.doOnTextChanged { text, _, _, _ ->
            viewModel.validateUsername(binding, text.toString())
        }

        etPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.validatePassword(binding, text.toString())
        }

        btnLogin.setOnClickListener {
            viewModel.login(
                binding,
                moduleIsShown,
                viewModel.getModuleName(),
                etUsername.text.toString(),
                etPassword.text.toString(),
            )
        }

        etUsername.setText("DEVAWAL")
        etPassword.setText("123456")

        ivSetting.setOnClickListener {
            showModule(true)
        }
    }

    override fun setupViewModel() {
        viewModel.login.observe(this) { state ->
            when(state) {
                is BaseResultState.Loading -> onLoading(true)
                is BaseResultState.Success -> onSuccessLogin(state.data)
                is BaseResultState.Error -> showError(state.error)
            }
        }

        viewModel.module.observe(this) { state ->
            when(state) {
                is BaseResultState.Loading -> onLoading(true)
                is BaseResultState.Success -> onSuccessModule(state.data)
                is BaseResultState.Error ->  showError(state.error)
            }
        }

        viewModel.errorMessage.observe(this) {
            showError(it)
        }

        viewModel.moduleSelected.observe(this) {
            setModuleName(it)
        }

        if (viewModel.getModuleName().isNullOrEmpty()) {
            binding.tvModule.text = getString(R.string.title_choose_module)
            showModule(true)
        } else {
            binding.tvModule.text = viewModel.getModuleName()
            showModule(false)
        }

        viewModel.getModule()
    }

    private fun setModuleName(data: ModuleItem) = with(binding) {
        tvModule.text = data.nama?.convertToCamelCase()
        module = data.nama.toString()
        viewModel.validateModule(binding, data.nama)
    }

    private fun onSuccessModule(data: List<ModuleItem>) {
        onLoading(false)
        viewModel.setModuleList(data)
        binding.tvModule.setOnClickListener {
            ModuleBottomSheet().show(this.supportFragmentManager, "")
        }
    }

    private fun onSuccessLogin(data: UserItem) {
        viewModel.setUser(data)
        onLoading(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun onLoading(isLoading: Boolean) = with(binding){
        uikitLoading.setLoadingProgress(isLoading)
        if (isLoading) {
            uikitLoading.show()
            btnLogin.hide()
            btnLogin.isEnabled = false
        } else {
            uikitLoading.hide()
            btnLogin.show()
            btnLogin.isEnabled = true
        }
    }

    private fun showError(error: Throwable?) {
        onLoading(false)
        when(error) {
            is HttpException -> {
                Snackbar.make(
                    binding.root,
                    ErrorMapper.errorMapperlogin(error)?.password ?: getString(R.string.title_error_des),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            is IOException -> {
                Snackbar.make(
                    binding.root,
                    getString(R.string.title_no_internet_connection),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun showError(message: String?) {
        onLoading(false)
        message?.let { Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show() }
    }

    private fun showModule(isShown: Boolean) = with(binding){
        if (isShown) {
            moduleIsShown = true
            tvModuleLabel.show()
            tvModule.show()
        } else {
            moduleIsShown = false
            tvModuleLabel.hide()
            tvModule.hide()
        }
    }
}