package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        if (isUserLoggedIn()) {
            val intent = Intent(this, BottomNavActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sp: SharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val username: String? = sp.getString("username", null)
        val email: String? = sp.getString("email", null)
        return username != null && email != null
    }
}