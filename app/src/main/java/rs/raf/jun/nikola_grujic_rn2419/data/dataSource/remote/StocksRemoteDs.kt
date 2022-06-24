package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote

import retrofit2.http.GET
import rs.raf.jun.nikola_grujic_rn2419.data.model.IndexesResponse

interface StocksRemoteDs {
    @GET("api/ngrujic2419rn/getIndexes")
    suspend fun fetchStocks(): IndexesResponse
}