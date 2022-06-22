package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.jun.nikola_grujic_rn2419.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DefaultAppTheme)

        if (isUserLoggedIn()) {
            // go to main screen
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