package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock

@Dao
interface StocksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStock(stock: BoughtStock)

    @Query("SELECT * FROM stocks WHERE username = :username AND symbol = :symbol")
    fun getBoughtStock(username: String, symbol: String): BoughtStock?

    @Query("SELECT * FROM stocks WHERE username = :username")
    fun getBoughtStocks(username: String): List<BoughtStock>?

    @Query("DELETE FROM stocks WHERE username = :username AND amount = 0")
    fun deleteStock(username: String)
}