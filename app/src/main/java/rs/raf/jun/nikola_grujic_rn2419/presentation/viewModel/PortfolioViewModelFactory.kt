package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PortfolioViewModelFactory(private var application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortfolioViewModelImpl::class.java)) {
            return PortfolioViewModelImpl(application) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}