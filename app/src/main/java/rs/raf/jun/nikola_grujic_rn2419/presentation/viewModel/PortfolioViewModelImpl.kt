package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PortfolioViewModelImpl : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Portfolio Fragment"
    }
    val text: LiveData<String> = _text
}