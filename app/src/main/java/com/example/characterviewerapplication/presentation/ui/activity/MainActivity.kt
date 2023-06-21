package com.example.characterviewerapplication.presentation.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.characterviewerapplication.R
import androidx.appcompat.widget.Toolbar
import com.example.characterviewerapplication.domain.utilities.UtilityManager.isTablet

/**
 * Main activity class for Phone devices
 */
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(if (isTablet(resources)) R.layout.activity_main_tablet else R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)

        // Set up the ActionBar with the NavController for back button handling
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
