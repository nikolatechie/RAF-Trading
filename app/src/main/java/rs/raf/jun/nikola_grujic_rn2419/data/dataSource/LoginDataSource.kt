package rs.raf.jun.nikola_grujic_rn2419.data.dataSource

interface LoginDataSource {
    fun checkIfUserExists(username: String, email: String, password: String): Boolean
}