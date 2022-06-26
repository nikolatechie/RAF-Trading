package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock

@Dao
interface StocksDao {
    @Insert
    fun addStock(stock: BoughtStock)

    @Query("SELECT * FROM stocks WHERE username = :username")
    fun getBoughtStocks(username: String): List<BoughtStock>?
}