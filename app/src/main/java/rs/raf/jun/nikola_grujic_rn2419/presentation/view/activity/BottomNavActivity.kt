package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.databinding.ActivityBottomNavBinding

class BottomNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_nav)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_discover, R.id.navigation_portfolio, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}