package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class BuyViewModelFactory(private var application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuyViewModelImpl::class.java)) {
            return BuyViewModelImpl(application) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}