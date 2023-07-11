package id.siandalan.app.features.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import id.siandalan.app.R
import id.siandalan.app.common.base.BaseActivity
import id.siandalan.app.common.utils.hide
import id.siandalan.app.common.utils.show
import id.siandalan.app.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setupView(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
        onDestinationChanged(navHostFragment.navController)
    }

    private fun onDestinationChanged(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> showNavigation()
                R.id.navigation_request -> showNavigation()
                else -> hideNavigation()
            }
        }
    }

    private fun showNavigation() = with(binding){
        bottomNavigation.show()
        bottomAppBar.show()
    }

    private fun hideNavigation() = with(binding) {
        bottomNavigation.hide()
        bottomAppBar.hide()
    }

    override fun setupViewModel() {

    }

}