package rs.raf.jun.nikola_grujic_rn2419.data.repository

import android.util.Log
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.NewsRemoteDs
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.Retrofit
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.StocksRemoteDs
import rs.raf.jun.nikola_grujic_rn2419.data.model.News
import rs.raf.jun.nikola_grujic_rn2419.data.model.Quote

class NewsRepositoryImpl: NewsRepository {
    override suspend fun fetchNews(): List<News> {
        // remote data source
        return try {
            Retrofit
                .getInstance("https://testapi.io")
                .create(NewsRemoteDs::class.java)
                .fetchNews()
                .data.newsItems
        } catch (e: Exception) {
            Log.d("ERROR", "Retrofit - News")
            ArrayList()
        }
    }

    override suspend fun fetchStocks(): List<Quote> {
        // remote data source
        return try {
            Retrofit
                .getInstance("https://testapi.io")
                .create(StocksRemoteDs::class.java)
                .fetchStocks()
                .data.quotes
        } catch (e: Exception) {
            Log.d("ERROR", "Retrofit - Stocks")
            ArrayList()
        }
    }
}