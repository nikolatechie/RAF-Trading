package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock
import rs.raf.jun.nikola_grujic_rn2419.data.repository.*

class PortfolioViewModelImpl : PortfolioViewModel, ViewModel(), KoinComponent {
    private val accRepo: AccountRepository by inject()
    private val portRepo: PortfolioRepository by inject()
    private val stockRepo: StocksRepository by inject()
    private val quoteRepo: DetailsRepository by inject()
    override val accountResponse: MutableLiveData<AccountInfo?> = MutableLiveData()
    override val portfolioResponse: MutableLiveData<List<PortfolioHistory>?> = MutableLiveData()
    override val stocksResponse: MutableLiveData<List<BoughtStock>?> = MutableLiveData()
    override val quoteResponse: MutableLiveData<Stock> = MutableLiveData()

    override fun getAccountInfo(username: String) {
        viewModelScope.launch {
            val response = accRepo.getAccountInfo(username)
            accountResponse.value = response
        }
    }

    override fun getPortfolioHistory(username: String) {
        viewModelScope.launch {
            val response = portRepo.getPortfolioHistory(username)
            portfolioResponse.value = response
        }
    }

    override fun getBoughtStocks(username: String) {
        viewModelScope.launch {
            val response = stockRepo.getBoughtStocks(username)
            stocksResponse.value = response
        }
    }

    override fun fetchStock(symbol: String) {
        viewModelScope.launch {
            val response = quoteRepo.fetchStock(symbol)
            quoteResponse.value = response
        }
    }
}