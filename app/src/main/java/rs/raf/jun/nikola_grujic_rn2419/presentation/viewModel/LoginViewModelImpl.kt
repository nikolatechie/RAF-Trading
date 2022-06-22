package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import rs.raf.jun.nikola_grujic_rn2419.data.repository.login.LoginRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.login.LoginRepositoryImpl

class LoginViewModelImpl : ViewModel(), LoginViewModel {
    private val loginRepo: LoginRepository = LoginRepositoryImpl()

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