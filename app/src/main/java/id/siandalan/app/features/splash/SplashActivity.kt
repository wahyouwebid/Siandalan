package id.siandalan.app.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.common.base.BaseActivity
import id.siandalan.app.databinding.ActivitySplashBinding
import id.siandalan.app.features.login.presentation.LoginActivity
import id.siandalan.app.features.login.presentation.LoginViewModel
import id.siandalan.app.features.main.MainActivity

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
        Handler(mainLooper).postDelayed({
            if (viewModel.getIsLogin()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }, 3000)
    }

    override fun setupViewModel() {}

}