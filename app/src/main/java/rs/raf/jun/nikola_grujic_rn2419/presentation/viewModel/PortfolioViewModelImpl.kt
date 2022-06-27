package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock
import rs.raf.jun.nikola_grujic_rn2419.data.repository.*

class PortfolioViewModelImpl(application: Application) : PortfolioViewModel, ViewModel() {
    private val accRepo: AccountRepository = AccountRepositoryImpl(application)
    private val portRepo: PortfolioRepository = PortfolioRepositoryImpl(application)
    private val stockRepo: StocksRepository = StocksRepositoryImpl(application)
    private val quoteRepo: DetailsRepository = DetailsRepositoryImpl()
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