package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.model.News
import rs.raf.jun.nikola_grujic_rn2419.data.model.Quote

interface NewsRepository {
    suspend fun fetchNews(): List<News>
    suspend fun fetchStocks(): List<Quote>
}