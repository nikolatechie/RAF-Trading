package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

interface PortfolioRepository {
    suspend fun addPortfolioHistory(portfolio: PortfolioHistory)
    suspend fun getPortfolioHistory(username: String): List<PortfolioHistory>?
}