package com.example.testmarket

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val navHost =
      supportFragmentManager.findFragmentById(R.id.fragmentContainerApp) as NavHostFragment
    navController = navHost.navController
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp()
  }

  private var doubleBackToExitPressedOnce = false
  override fun onBackPressed() {
    if (doubleBackToExitPressedOnce) {
      super.onBackPressed()
    } else {
      doubleBackToExitPressedOnce = true
      Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
      Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
  }
}