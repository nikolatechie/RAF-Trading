package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote

import retrofit2.http.GET
import rs.raf.jun.nikola_grujic_rn2419.data.model.NewsResponse

interface NewsRemoteDs {
    @GET("api/ngrujic00/getNews")
    suspend fun fetchNews(): NewsResponse
}