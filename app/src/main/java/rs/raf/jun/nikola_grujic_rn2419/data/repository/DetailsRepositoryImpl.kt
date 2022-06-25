package rs.raf.jun.nikola_grujic_rn2419.data.repository

import android.util.Log
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.QuoteRemoteDs
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.remote.Retrofit
import rs.raf.jun.nikola_grujic_rn2419.data.model.Chart
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock
import rs.raf.jun.nikola_grujic_rn2419.data.model.StockMetrics
import java.lang.Exception

class DetailsRepositoryImpl: DetailsRepository {
    override suspend fun fetchStock(symbol: String): Stock {
        // remote data source
        return try {
            Retrofit
                .getInstance("https://testapi.io")
                .create(QuoteRemoteDs::class.java)
                .fetchStock()
                .data
        } catch (e: Exception) {
            Log.d("ERROR", "Retrofit - Details")
            Stock("INVALID", "INVALID", "INVALID", "INVALID",
                0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(),
                StockMetrics(0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble()),
                Chart(ArrayList())
            )
        }
    }
}