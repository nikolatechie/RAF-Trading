package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.AccountDao
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo

class AccountRepositoryImpl(private val accountDao: AccountDao): AccountRepository {
    override suspend fun addAccountInfo(accountInfo: AccountInfo) {
        accountDao.addAccountInfo(accountInfo)
    }

    override suspend fun getAccountInfo(username: String): AccountInfo? {
        return accountDao.getAccountInfo(username)
    }
}