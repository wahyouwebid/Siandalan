package id.siandalan.app.features.login.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.features.main.MainActivity
import id.siandalan.app.common.base.BaseActivity
import id.siandalan.app.databinding.ActivityLoginBinding

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
        binding.btnLogin?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun setupViewModel() {

    }

}