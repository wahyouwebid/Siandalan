package id.siandalan.app.features.login.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.features.main.MainActivity
import id.siandalan.app.common.base.BaseActivity
import id.siandalan.app.common.firebase.FirebaseUtils
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.ActivityLoginBinding
import id.siandalan.app.features.login.domain.state.LoginResultState

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

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
                etUsername.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    override fun setupViewModel() {
        viewModel.login.observe(this) { state ->
            when(state) {
                is LoginResultState.Loading -> onLoading(true)
                is LoginResultState.Success -> onSuccess()
                is LoginResultState.Error -> onSuccess()
            }
        }

        viewModel.errorMessage.observe(this) {
            showError(it)
        }
    }

    private fun onLoading(isLoading: Boolean) = with(binding){
        uikitLoading.setLoadingProgress(isLoading)
        if (isLoading) {
            uikitLoading.show()
            btnLogin.hide()
        } else {
            uikitLoading.hide()
            btnLogin.show()
        }
    }
    private fun onSuccess() {
        onLoading(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showError(message: String) {
        onLoading(false)
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}