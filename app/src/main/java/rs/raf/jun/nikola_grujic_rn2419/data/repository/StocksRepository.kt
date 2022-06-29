package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock

interface StocksRepository {
    suspend fun addStock(stock: BoughtStock)
    suspend fun getBoughtStock(username: String, symbol: String): BoughtStock?
    suspend fun getBoughtStocks(username: String): List<BoughtStock>?
    suspend fun deleteStock(username: String)
}