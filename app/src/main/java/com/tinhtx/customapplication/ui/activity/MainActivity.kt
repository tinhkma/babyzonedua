package com.tinhtx.customapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseActivity
import com.tinhtx.customapplication.base.setVisibility
import com.tinhtx.customapplication.base.showBackArrow
import com.tinhtx.customapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    NavController.OnDestinationChangedListener {

    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override val layoutRes: Int = R.layout.activity_main

    override fun onDataBound(binding: ActivityMainBinding) {
        binding.viewModel = viewModel
        getNavController().addOnDestinationChangedListener(this)
    }

    private fun Toolbar.setup() {
        setSupportActionBar(this)
        setupActionBarWithNavController(getNavController())
    }

    private fun getNavController(): NavController {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        setupWithNavController(binding.bottomNavigationView, navController)
        return navController
    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle?
    ) {
        invalidateOptionsMenu()
        when (destination.id) {
            R.id.navigation_home -> {
                supportActionBar?.showBackArrow(false)
            }

            else -> {
                supportActionBar?.showBackArrow(true)
            }
        }
    }

    override fun onBackPressed() {
        when (getNavController().currentDestination?.id) {
            R.id.navigation_home -> finish()
            else -> getNavController().navigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        getNavController().navigateUp()
        return true
    }
}