package com.example.taskmanagement_android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomAppBar = findViewById(R.id.bottomAppBar)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomFab = findViewById(R.id.fab)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.welcomeFragment,
                R.id.loginFragment,
                R.id.registerFragment,
                R.id.addTaskFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }

        setupBottomNav()
    }

    private fun hideBottomNav() {
        bottomAppBar.visibility = View.GONE
        bottomFab.visibility = View.GONE
        bottomNavigationView.visibility = View.GONE
    }

    private fun showBottomNav() {
        bottomAppBar.visibility = View.VISIBLE
        bottomFab.visibility = View.VISIBLE
        bottomNavigationView.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupBottomNav() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.taskListFragment -> {
                    navController.navigate(R.id.taskListFragment)
                    true
                }
                R.id.addTaskFragment -> {
                    navController.navigate(R.id.addTaskFragment)
                    true
                }
                else -> false
            }
        }

        bottomFab.setOnClickListener {
            navController.navigate(R.id.addTaskFragment)
        }
    }
}
