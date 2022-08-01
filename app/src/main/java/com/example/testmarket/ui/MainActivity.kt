package com.example.testmarket.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testmarket.R
import com.example.testmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val navHost =
      supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
    navController = navHost.navController
    //NavigationUI.setupActionBarWithNavController(this, navController)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp()
  }
}