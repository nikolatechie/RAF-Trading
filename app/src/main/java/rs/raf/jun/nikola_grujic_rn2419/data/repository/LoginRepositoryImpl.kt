package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.LoginDataSource

class LoginRepositoryImpl(private val loginDs: LoginDataSource): LoginRepository {
    override fun checkIfUserExists(username: String, email: String, password: String): Boolean {
        return loginDs.checkIfUserExists(username, email, password)
    }
}