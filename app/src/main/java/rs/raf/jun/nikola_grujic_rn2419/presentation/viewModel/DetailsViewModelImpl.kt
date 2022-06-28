package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock
import rs.raf.jun.nikola_grujic_rn2419.data.repository.DetailsRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.DetailsRepositoryImpl
import rs.raf.jun.nikola_grujic_rn2419.data.repository.StocksRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.StocksRepositoryImpl

class DetailsViewModelImpl(application: Application) : DetailsViewModel, ViewModel() {
    private val detailsRepo: DetailsRepository = DetailsRepositoryImpl()
    private val stocksRepo: StocksRepository = StocksRepositoryImpl(application)
    override val stock: MutableLiveData<Stock> = MutableLiveData()

    override fun fetchStock(symbol: String) {
        viewModelScope.launch {
            val response = detailsRepo.fetchStock(symbol)
            stock.value = response
        }
    }

    override fun getBoughtStock(username: String, symbol: String): BoughtStock? {
        return stocksRepo.getBoughtStock(username, symbol)
    }
}