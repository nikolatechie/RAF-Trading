package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.presentation.contract.LoginViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.LoginViewModelImpl

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[LoginViewModelImpl::class.java]

        val loginBtn: Button = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()

            val errorMsg: String? = viewModel.validateInput(username, email, password)

            if (errorMsg != null)
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            else {
                val userExists = viewModel.checkIfUserExists(username, email, password)

                if (userExists) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                    val sp: SharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                    val editor = sp.edit()
                    editor.putString("username", username)
                    editor.putString("email", email)
                    editor.apply()
                }
                else
                    Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_LONG).show()
            }
        }
    }
}