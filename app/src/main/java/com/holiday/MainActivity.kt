package com.holiday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.holiday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let {
            setContentView(it.root)

            setBottomNavController()
        }
    }

    private fun setBottomNavController() {
        binding?.let {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

            navHostFragment.navController.apply {
                it.bottomNavigation.setupWithNavController(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
