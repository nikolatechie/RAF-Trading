package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

interface PortfolioViewModel {
    fun getAccountInfo(username: String): AccountInfo?
    fun getPortfolioHistory(username: String): List<PortfolioHistory>?
    fun getBoughtStocks(username: String): List<BoughtStock>?
}