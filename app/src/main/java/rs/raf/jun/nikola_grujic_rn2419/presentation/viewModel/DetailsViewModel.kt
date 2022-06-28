package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock

interface DetailsViewModel {
    val stock: MutableLiveData<Stock>

    fun fetchStock(symbol: String)
    fun getBoughtStock(username: String, symbol: String): BoughtStock?
}