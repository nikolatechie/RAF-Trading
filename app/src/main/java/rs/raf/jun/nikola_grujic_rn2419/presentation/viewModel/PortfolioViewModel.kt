package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

interface PortfolioViewModel {
    val accountResponse: MutableLiveData<AccountInfo?>

    fun getAccountInfo(username: String)
    fun getPortfolioHistory(username: String): List<PortfolioHistory>?
    fun getBoughtStocks(username: String): List<BoughtStock>?
}