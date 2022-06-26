package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.data.repository.AccountRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.AccountRepositoryImpl
import rs.raf.jun.nikola_grujic_rn2419.data.repository.PortfolioRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.PortfolioRepositoryImpl

class PortfolioViewModelImpl(application: Application) : PortfolioViewModel, ViewModel() {
    private val accRepo: AccountRepository = AccountRepositoryImpl(application)
    private val portRepo: PortfolioRepository = PortfolioRepositoryImpl(application)

    override fun getAccountInfo(username: String): AccountInfo? {
        return accRepo.getAccountInfo(username)
    }

    override fun getPortfolioHistory(username: String): List<PortfolioHistory>? {
        return portRepo.getPortfolioHistory(username)
    }

    override fun getBoughtStocks(username: String): List<BoughtStock>? {
        TODO("Not yet implemented")
    }

}