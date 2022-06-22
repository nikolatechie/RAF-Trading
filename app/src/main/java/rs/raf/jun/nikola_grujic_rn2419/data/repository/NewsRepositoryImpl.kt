package rs.raf.jun.nikola_grujic_rn2419.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.NewsRemoteDs
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.Retrofit
import rs.raf.jun.nikola_grujic_rn2419.data.model.NewsResponse

class NewsRepositoryImpl: NewsRepository {
    override suspend fun fetchNews(): NewsResponse {
        Log.d("POKUSAVAM", "IMA LI")
        return Retrofit
            .getInstance("https://b311eca0-15ae-40e9-9185-c6d4da7ae2c7.mock.pstmn.io")
            .create(NewsRemoteDs::class.java)
            .fetchNews()
    }
}