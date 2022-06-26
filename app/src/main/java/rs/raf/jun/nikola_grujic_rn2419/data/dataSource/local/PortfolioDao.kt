package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

@Dao
interface PortfolioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPortfolioHistory(history: PortfolioHistory)

    @Query("SELECT * FROM history WHERE username = :username")
    fun getPortfolioHistory(username: String): List<PortfolioHistory>?
}