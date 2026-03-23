package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), PlayerFragment.PlayerListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView
    private var currentPlay: String = "Pedra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        currentPlay = savedInstanceState?.getString(KEY_CURRENT_PLAY) ?: "Pedra"
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        bottomNav = findViewById(R.id.bottomNavigation)
        val navView = findViewById<NavigationView>(R.id.navigationView)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.playerFragment,
                R.id.resultFragment
            ),
            drawerLayout
        )

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
        setupBottomNavigation()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.visibility =
                if (destination.id == R.id.homeFragment) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_CURRENT_PLAY, currentPlay)
        logLifecycle("onSaveInstanceState")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_restart -> {
                navController.navigate(
                    R.id.homeFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.nav_graph, true)
                        .build()
                )
                true
            }

            else -> NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
            || super.onSupportNavigateUp()
    }

    override fun onPlaySelected(selectedPlay: String) {
        currentPlay = selectedPlay
        Log.d(TAG, "Current play selected: $currentPlay")
    }

    private fun setupBottomNavigation() {
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.playerFragment -> {
                    navController.navigate(R.id.playerFragment)
                    true
                }

                R.id.resultFragment -> {
                    val args = Bundle().apply {
                        putString(ResultFragment.ARG_CURRENT_PLAY, currentPlay)
                    }
                    navController.navigate(
                        R.id.resultFragment,
                        args
                    )
                    true
                }

                else -> false
            }
        }
    }

    private fun logLifecycle(event: String) {
        Log.d(TAG, "MainActivity -> $event")
    }

    companion object {
        private const val TAG = "Lifecycle"
        private const val KEY_CURRENT_PLAY = "key_current_play"
    }
}
