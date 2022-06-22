package rs.raf.jun.nikola_grujic_rn2419.data.repository

import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.model.NewsResponse

interface NewsRepository {
    suspend fun fetchNews(): NewsResponse
}