package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock
import rs.raf.jun.nikola_grujic_rn2419.data.repository.DetailsRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.DetailsRepositoryImpl

class DetailsViewModelImpl : DetailsViewModel, ViewModel() {
    private val repo: DetailsRepository = DetailsRepositoryImpl()
    override val stock: MutableLiveData<Stock> = MutableLiveData()

    override fun fetchStock(symbol: String) {
        viewModelScope.launch {
            val response = repo.fetchStock(symbol)
            stock.value = response
        }
    }
}