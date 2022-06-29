package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

interface BuySellViewModel {
    val accResponse: MutableLiveData<AccountInfo?>
    val portResponse: MutableLiveData<List<PortfolioHistory>?>
    val stockResponse: MutableLiveData<BoughtStock?>
    val stocksResponse: MutableLiveData<List<BoughtStock>?>

    fun addAccountInfo(accountInfo: AccountInfo)
    fun getAccountInfo(username: String)

    fun addPortfolioHistory(portfolio: PortfolioHistory)
    fun getPortfolioHistory(username: String)

    fun addBoughtStock(stock: BoughtStock)
    fun getBoughtStock(username: String, symbol: String)
    fun getBoughtStocks(username: String)
    fun deleteStock(username: String)
}