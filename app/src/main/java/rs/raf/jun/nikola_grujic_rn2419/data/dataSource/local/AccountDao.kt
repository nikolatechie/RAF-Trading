package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAccountInfo(accountInfo: AccountInfo)

    @Query("SELECT * FROM account WHERE username = :username")
    fun getAccountInfo(username: String): AccountInfo?
}