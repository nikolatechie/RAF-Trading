package rs.raf.jun.nikola_grujic_rn2419.data.repository

import android.app.Application
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.AccountDao
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.RafDatabase
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo

class AccountRepositoryImpl(application: Application): AccountRepository {
    private val accountDao: AccountDao = RafDatabase.getDatabase(application).accountDao()

    override fun addAccountInfo(accountInfo: AccountInfo) {
        accountDao.addAccountInfo(accountInfo)
    }

    override fun getAccountInfo(username: String): AccountInfo? {
        return accountDao.getAccountInfo(username)
    }
}