package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock

interface PortfolioViewModel {
    val accountResponse: MutableLiveData<AccountInfo?>
    val portfolioResponse: MutableLiveData<List<PortfolioHistory>?>
    val stocksResponse: MutableLiveData<List<BoughtStock>?>
    val quoteResponse: MutableLiveData<Stock>

    fun getAccountInfo(username: String)
    fun getPortfolioHistory(username: String)
    fun getBoughtStocks(username: String)

    fun fetchStock(symbol: String)
}