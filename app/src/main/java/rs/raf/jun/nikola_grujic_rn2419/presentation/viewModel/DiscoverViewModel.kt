package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.model.NewsResponse

interface DiscoverViewModel {
    val newsResponse: MutableLiveData<NewsResponse>

    fun fetchNews()
}