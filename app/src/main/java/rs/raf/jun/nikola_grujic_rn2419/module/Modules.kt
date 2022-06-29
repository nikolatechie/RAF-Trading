package rs.raf.jun.nikola_grujic_rn2419.module

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.LoginDataSource
import rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local.*
import rs.raf.jun.nikola_grujic_rn2419.data.repository.*
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.*

val appModule = module {
    single { RafDatabase.getDatabase(androidApplication()) }

    single<AccountDao> { RafDatabase.getDatabase(androidApplication()).accountDao() }

    factory<AccountRepository> { AccountRepositoryImpl(get()) }

    factory<DetailsRepository> { DetailsRepositoryImpl() }

    single<LoginDataSource> { LoginDb() }

    factory<LoginRepository> { LoginRepositoryImpl(get()) }

    factory<NewsRepository> { NewsRepositoryImpl() }

    single<PortfolioDao> { RafDatabase.getDatabase(androidApplication()).portfolioDao() }

    factory<PortfolioRepository> { PortfolioRepositoryImpl(get()) }

    single<StocksDao> { RafDatabase.getDatabase(androidApplication()).stocksDao() }

    factory<StocksRepository> { StocksRepositoryImpl(get()) }

    viewModel { BuySellViewModelImpl() }

    viewModel { LoginViewModelImpl() }

    viewModel { DetailsViewModelImpl() }

    viewModel { DiscoverViewModelImpl() }

    viewModel { PortfolioViewModelImpl() }
}