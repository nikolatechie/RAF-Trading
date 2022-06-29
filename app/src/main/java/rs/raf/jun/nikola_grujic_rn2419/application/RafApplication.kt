package rs.raf.jun.nikola_grujic_rn2419.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.jun.nikola_grujic_rn2419.BuildConfig
import rs.raf.jun.nikola_grujic_rn2419.module.appModule

class RafApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        val modules = listOf(
            appModule
        )

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@RafApplication)
            modules(modules)
        }
    }
}