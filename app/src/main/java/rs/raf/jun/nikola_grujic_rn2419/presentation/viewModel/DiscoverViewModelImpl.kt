package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.raf.jun.nikola_grujic_rn2419.data.model.News
import rs.raf.jun.nikola_grujic_rn2419.data.repository.NewsRepository
import rs.raf.jun.nikola_grujic_rn2419.data.repository.NewsRepositoryImpl

class DiscoverViewModelImpl : DiscoverViewModel, ViewModel() {
    private val newsRepo: NewsRepository = NewsRepositoryImpl()
    override val newsResponse: MutableLiveData<List<News>> = MutableLiveData()

    override fun fetchNews() {
        viewModelScope.launch {
            val response = newsRepo.fetchNews()
            newsResponse.value = response
        }
    }
}