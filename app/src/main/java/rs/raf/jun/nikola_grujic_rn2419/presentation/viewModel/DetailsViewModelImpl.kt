package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock
import rs.raf.jun.nikola_grujic_rn2419.data.repository.DetailsRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.StocksRepository

class DetailsViewModelImpl : DetailsViewModel, ViewModel(), KoinComponent {
    private val detailsRepo: DetailsRepository by inject()
    private val stocksRepo: StocksRepository by inject()
    override val stock: MutableLiveData<Stock> = MutableLiveData()
    override val boughtStock: MutableLiveData<BoughtStock?> = MutableLiveData()

    override fun fetchStock(symbol: String) {
        viewModelScope.launch {
            val response = detailsRepo.fetchStock(symbol)
            stock.value = response
        }
    }

    override fun getBoughtStock(username: String, symbol: String) {
        viewModelScope.launch {
            val response = stocksRepo.getBoughtStock(username, symbol)
            boughtStock.value = response
        }
    }
}