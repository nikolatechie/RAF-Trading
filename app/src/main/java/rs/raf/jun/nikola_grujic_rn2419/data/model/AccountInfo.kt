package rs.raf.jun.nikola_grujic_rn2419.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountInfo (
    @PrimaryKey
    val username: String,
    val balance: Double,
    val portfolio: Double
)