package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class GameActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var tvPlayerTitle: TextView
    private lateinit var tvSelectMove: TextView
    private lateinit var spinnerMove: Spinner
    private lateinit var tvResultTitle: TextView
    private lateinit var tvResultWinner: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setupViews()
        setupToolbarAndDrawer()
        setupSpinner()
        setupBottomNavigation()
        setupDrawerNavigation()
        showPlayerScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_restart -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViews() {
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        tvPlayerTitle = findViewById(R.id.tvPlayerTitle)
        tvSelectMove = findViewById(R.id.tvSelectMove)
        spinnerMove = findViewById(R.id.spinnerMove)
        tvResultTitle = findViewById(R.id.tvResultTitle)
        tvResultWinner = findViewById(R.id.tvResultWinner)
    }

    private fun setupToolbarAndDrawer() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupSpinner() {
        val options = listOf("Pedra", "Tesoura", "Papel")
        val adapter = ArrayAdapter(
            this,
            R.layout.item_spinner_selected,
            options
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinnerMove.adapter = adapter
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_jogador -> {
                    showPlayerScreen()
                    true
                }

                R.id.nav_resultado -> {
                    showResultScreen()
                    true
                }

                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.nav_jogador
    }

    private fun setupDrawerNavigation() {
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_drawer_jokenpo -> {
                    drawerLayout.closeDrawers()
                    true
                }

                R.id.nav_drawer_jogador -> {
                    showPlayerScreen()
                    bottomNavigation.selectedItemId = R.id.nav_jogador
                    drawerLayout.closeDrawers()
                    true
                }

                R.id.nav_drawer_resultado -> {
                    showResultScreen()
                    bottomNavigation.selectedItemId = R.id.nav_resultado
                    drawerLayout.closeDrawers()
                    true
                }

                else -> false
            }
        }
    }

    private fun showPlayerScreen() {
        tvPlayerTitle.visibility = View.VISIBLE
        tvSelectMove.visibility = View.VISIBLE
        spinnerMove.visibility = View.VISIBLE

        tvResultTitle.visibility = View.GONE
        tvResultWinner.visibility = View.GONE
    }

    private fun showResultScreen() {
        tvPlayerTitle.visibility = View.GONE
        tvSelectMove.visibility = View.GONE
        spinnerMove.visibility = View.GONE

        tvResultTitle.visibility = View.VISIBLE
        tvResultWinner.visibility = View.VISIBLE
    }
}
