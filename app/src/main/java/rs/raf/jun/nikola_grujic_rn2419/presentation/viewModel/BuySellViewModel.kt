package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

interface BuySellViewModel {
    fun addAccountInfo(accountInfo: AccountInfo)
    fun getAccountInfo(username: String): AccountInfo?

    fun addPortfolioHistory(portfolio: PortfolioHistory)
    fun getPortfolioHistory(username: String): List<PortfolioHistory>?

    fun addBoughtStock(stock: BoughtStock)
    fun getBoughtStock(username: String, symbol: String): BoughtStock?
    fun getBoughtStocks(username: String): List<BoughtStock>?
    fun deleteStock(username: String)
}