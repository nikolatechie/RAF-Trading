package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.model.News
import rs.raf.jun.nikola_grujic_rn2419.data.model.Quote

interface DiscoverViewModel {
    val newsResponse: MutableLiveData<List<News>>
    val indexResponse: MutableLiveData<List<Quote>>

    fun fetchNews()
    fun fetchStocks()
}