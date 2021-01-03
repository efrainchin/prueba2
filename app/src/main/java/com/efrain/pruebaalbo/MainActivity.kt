package com.efrain.pruebaalbo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.efrain.pruebaalbo.databinding.ActivityMainBinding
import com.efrain.pruebaalbo.databinding.ActivityMainBindingImpl
import com.efrain.pruebaalbo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        observeMainViewModel()
        initializeNavigationUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        mainViewModel.setTile("")
        when(destination.id) {
            R.id.homeFragment -> isEnabledDisplayHome(false)
            else -> isEnabledDisplayHome(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun initializeNavigationUI() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setSupportActionBar(binding?.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        mainViewModel.setTile("")
        navController.addOnDestinationChangedListener(this)
    }

    private fun isEnabledDisplayHome(status: Boolean) {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(status)
            it.setDisplayShowHomeEnabled(status)
        }
    }

    private fun observeMainViewModel(){
        mainViewModel.getObserveTitle().observe(this, Observer {
            supportActionBar?.title = it
        })
    }

}