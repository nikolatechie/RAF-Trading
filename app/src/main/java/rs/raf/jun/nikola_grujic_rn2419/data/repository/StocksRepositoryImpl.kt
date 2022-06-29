package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.StocksDao
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock

class StocksRepositoryImpl(private val stocksDao: StocksDao): StocksRepository {
    override suspend fun addStock(stock: BoughtStock) {
        stocksDao.addStock(stock)
    }

    override suspend fun getBoughtStock(username: String, symbol: String): BoughtStock? {
        return stocksDao.getBoughtStock(username, symbol)
    }

    override suspend fun getBoughtStocks(username: String): List<BoughtStock>? {
        return stocksDao.getBoughtStocks(username)
    }

    override suspend fun deleteStock(username: String) {
        stocksDao.deleteStock(username)
    }
}