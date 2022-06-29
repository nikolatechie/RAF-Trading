package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import rs.raf.jun.nikola_grujic_rn2419.data.repository.LoginRepository

class LoginViewModelImpl : ViewModel(), LoginViewModel, KoinComponent {
    private val loginRepo: LoginRepository by inject()

    override fun validateInput(username: String, email: String, password: String): String? {
        return if (username.isEmpty() || email.isEmpty() || password.isEmpty())
            "The fields must NOT be empty"
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            "Invalid email"
        else if (password.length < 5)
            "Password must be of length 5 or more"
        else
            null
    }

    override fun checkIfUserExists(username: String, email: String, password: String): Boolean {
        return loginRepo.checkIfUserExists(username, email, password)
    }
}