package rs.raf.jun.nikola_grujic_rn2419.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class BoughtStock (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val symbol: String,
    val name: String,
    val price: Double,
    var amount: Int
)