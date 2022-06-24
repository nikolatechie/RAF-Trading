package rs.raf.jun.nikola_grujic_rn2419.data.repository

import android.util.Log
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.NewsRemoteDs
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.Retrofit
import rs.raf.jun.nikola_grujic_rn2419.data.model.News

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
            Log.d("ERROR", "Retrofit - NewsRepositoryImpl")
            ArrayList()
        }
    }
}