package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote

import retrofit2.http.GET
import rs.raf.jun.nikola_grujic_rn2419.data.model.StockResponse

interface QuoteRemoteDs {
    @GET("api/ngrujic2419rn/searchQuote_symbol=T")
    suspend fun fetchStock(): StockResponse
}