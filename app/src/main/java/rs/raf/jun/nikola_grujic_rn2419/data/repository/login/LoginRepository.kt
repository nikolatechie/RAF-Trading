package rs.raf.jun.nikola_grujic_rn2419.data.repository.login

interface LoginRepository {
    fun checkIfUserExists(username: String, email: String, password: String): Boolean
}