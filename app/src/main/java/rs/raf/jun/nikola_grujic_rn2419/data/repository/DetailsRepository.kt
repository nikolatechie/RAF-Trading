package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock

interface DetailsRepository {
    suspend fun fetchStock(symbol: String): Stock
}