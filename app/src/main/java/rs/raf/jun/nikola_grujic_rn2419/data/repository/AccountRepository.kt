package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo

interface AccountRepository {
    fun addAccountInfo(accountInfo: AccountInfo)
    fun getAccountInfo(username: String): AccountInfo?
}