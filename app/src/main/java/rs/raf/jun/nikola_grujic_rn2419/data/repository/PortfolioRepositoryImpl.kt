package rs.raf.jun.nikola_grujic_rn2419.data.repository

import android.app.Application
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.PortfolioDao
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.RafDatabase
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory

class PortfolioRepositoryImpl(application: Application): PortfolioRepository {
    private val portfolioDao: PortfolioDao = RafDatabase.getDatabase(application).portfolioDao()

    override fun addPortfolioHistory(portfolio: PortfolioHistory) {
        portfolioDao.addPortfolioHistory(portfolio)
    }

    override fun getPortfolioHistory(username: String): List<PortfolioHistory>? {
        return portfolioDao.getPortfolioHistory(username)
    }
}