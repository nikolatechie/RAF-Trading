package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.repository.AccountRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.AccountRepositoryImpl

class BuyViewModelImpl(application: Application): BuyViewModel, ViewModel() {
    private val repository: AccountRepository = AccountRepositoryImpl(application)

    override fun addAccountInfo(accountInfo: AccountInfo) {
        repository.addAccountInfo(accountInfo)
    }

    override fun getAccountInfo(username: String): AccountInfo? {
        return repository.getAccountInfo(username)
    }
}