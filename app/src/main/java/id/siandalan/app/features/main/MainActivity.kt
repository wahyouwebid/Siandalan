package id.siandalan.app.features.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseActivity
import id.siandalan.app.common.uikit.UIKitPopUp
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.ActivityMainBinding
import id.siandalan.app.features.login.presentation.LoginActivity
import id.siandalan.app.features.login.presentation.LoginViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
        onDestinationChanged(navHostFragment.navController)
        setupToolbar()
    }

    private fun setupToolbar() = with(binding){
        uikitToolbar.setToolbar(getString(R.string.app_name)) {
            UIKitPopUp(this@MainActivity).showPopup {
                viewModel.setIsLogin(false)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    private fun onDestinationChanged(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> showNavigation()
                R.id.navigation_request -> showNavigation()
                R.id.navigation_profile -> showNavigation()
                else -> hideNavigation()
            }
        }
    }

    private fun showNavigation() = with(binding){
        bottomNavigation.hide()
        bottomAppBar.hide()
        uikitToolbar.show()
    }

    private fun hideNavigation() = with(binding) {
        bottomNavigation.hide()
        bottomAppBar.hide()
        uikitToolbar.hide()
    }

    override fun setupViewModel() {}

}