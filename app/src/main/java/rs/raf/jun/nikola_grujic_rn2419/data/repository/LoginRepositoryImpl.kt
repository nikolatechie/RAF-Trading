package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.LoginDataSource
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.LoginDb

class LoginRepositoryImpl: LoginRepository {
    private val loginDs: LoginDataSource = LoginDb()

    override fun checkIfUserExists(username: String, email: String, password: String): Boolean {
        return loginDs.checkIfUserExists(username, email, password)
    }
}