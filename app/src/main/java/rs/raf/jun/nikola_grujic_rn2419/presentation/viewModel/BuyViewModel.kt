package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

interface BuyViewModel {
    fun addAccountInfo(accountInfo: AccountInfo)
    fun getAccountInfo(username: String): AccountInfo?

    fun addPortfolioHistory(portfolio: PortfolioHistory)
    fun getPortfolioHistory(username: String): List<PortfolioHistory>?
}