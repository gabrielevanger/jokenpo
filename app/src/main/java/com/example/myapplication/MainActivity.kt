package com.example.myapplication

import android.os.Bundle
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

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navView = findViewById<NavigationView>(R.id.navigationView)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.inicialFragment,
                R.id.jogadorFragment,
                R.id.resultadoFragment
            ),
            drawerLayout
        )

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupWithNavController(bottomNav, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.visibility =
                if (destination.id == R.id.inicialFragment) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_restart -> {
                navController.navigate(
                    R.id.inicialFragment,
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
}
