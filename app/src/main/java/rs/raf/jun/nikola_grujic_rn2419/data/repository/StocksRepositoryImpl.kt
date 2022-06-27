package rs.raf.jun.nikola_grujic_rn2419.data.repository

import android.app.Application
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.RafDatabase
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.StocksDao
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock

class StocksRepositoryImpl(application: Application): StocksRepository {
    private val stocksDao: StocksDao = RafDatabase.getDatabase(application).stocksDao()

    override fun addStock(stock: BoughtStock) {
        stocksDao.addStock(stock)
    }

    override fun getBoughtStock(username: String, symbol: String): BoughtStock? {
        return stocksDao.getBoughtStock(username, symbol)
    }

    override fun getBoughtStocks(username: String): List<BoughtStock>? {
        return stocksDao.getBoughtStocks(username)
    }
}