package rs.raf.jun.nikola_grujic_rn2419.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class PortfolioHistory (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val value: Double
)