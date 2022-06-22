package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote

import androidx.lifecycle.MutableLiveData
import retrofit2.http.GET
import rs.raf.jun.nikola_grujic_rn2419.data.model.NewsResponse

interface NewsRemoteDs {
    @GET("rma/getNews")
    suspend fun fetchNews(): NewsResponse
}