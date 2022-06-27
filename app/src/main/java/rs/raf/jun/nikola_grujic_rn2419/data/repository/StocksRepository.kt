package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock

interface StocksRepository {
    fun addStock(stock: BoughtStock)
    fun getBoughtStock(username: String, symbol: String): BoughtStock?
    fun getBoughtStocks(username: String): List<BoughtStock>?
}