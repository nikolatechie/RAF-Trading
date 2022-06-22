package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local

import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.LoginDataSource

class LoginDb: LoginDataSource {
    companion object {
        private val users: List<String> = listOf("ngrujic2419rn ngrujic2419rn@raf.rs nikola123")
    }

    override fun checkIfUserExists(username: String, email: String, password: String): Boolean {
        for (user in users) {
            if (username == user.split(" ")[0] &&
                email == user.split(" ")[1] &&
                password == user.split(" ")[2]) {
                return true
            }
        }

        return false
    }
}