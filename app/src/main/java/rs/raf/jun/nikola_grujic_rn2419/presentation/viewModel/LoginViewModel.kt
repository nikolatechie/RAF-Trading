package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

interface LoginViewModel {
    fun validateInput(username: String, email: String, password: String): String?
    fun checkIfUserExists(username: String, email: String, password: String): Boolean
}