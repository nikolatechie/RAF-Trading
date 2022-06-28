package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.data.repository.*

class BuySellViewModelImpl(application: Application): BuySellViewModel, ViewModel() {
    private val accRepo: AccountRepository = AccountRepositoryImpl(application)
    private val portRepo: PortfolioRepository = PortfolioRepositoryImpl(application)
    private val stockRepo: StocksRepository = StocksRepositoryImpl(application)

    override fun addAccountInfo(accountInfo: AccountInfo) {
        accRepo.addAccountInfo(accountInfo)
    }

    override fun getAccountInfo(username: String): AccountInfo? {
        return accRepo.getAccountInfo(username)
    }

    override fun addPortfolioHistory(portfolio: PortfolioHistory) {
        portRepo.addPortfolioHistory(portfolio)
    }

    override fun getPortfolioHistory(username: String): List<PortfolioHistory>? {
        return portRepo.getPortfolioHistory(username)
    }

    override fun addBoughtStock(stock: BoughtStock) {
        stockRepo.addStock(stock)
    }

    override fun getBoughtStock(username: String, symbol: String): BoughtStock? {
        return stockRepo.getBoughtStock(username, symbol)
    }

    override fun getBoughtStocks(username: String): List<BoughtStock>? {
        return stockRepo.getBoughtStocks(username)
    }

    override fun deleteStock(username: String) {
        stockRepo.deleteStock(username)
    }
}