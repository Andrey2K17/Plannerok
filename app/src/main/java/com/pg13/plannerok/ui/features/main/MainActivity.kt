package com.pg13.plannerok.ui.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pg13.plannerok.R
import com.pg13.plannerok.utils.launchOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        return@lazy navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: MainViewModel by viewModels()

        launchOnLifecycle {
            viewModel.authData.collect {
                if (it.accessToken?.isEmpty() != false) {
                    navController.navigate(R.id.signInFragment)
                } else {
                    navController.popBackStack(R.id.signInFragment, true)
                    navController.navigate(R.id.profileFragment)
                }
            }
        }

    }
}