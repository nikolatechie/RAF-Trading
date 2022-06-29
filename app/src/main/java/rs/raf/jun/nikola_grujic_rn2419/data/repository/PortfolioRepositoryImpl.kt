package rs.raf.jun.nikola_grujic_rn2419.data.repository

import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.PortfolioDao
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

class PortfolioRepositoryImpl(private val portfolioDao: PortfolioDao): PortfolioRepository {
    override suspend fun addPortfolioHistory(portfolio: PortfolioHistory) {
        portfolioDao.addPortfolioHistory(portfolio)
    }

    override suspend fun getPortfolioHistory(username: String): List<PortfolioHistory>? {
        return portfolioDao.getPortfolioHistory(username)
    }
}