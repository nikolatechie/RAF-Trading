package rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiscoverViewModelImpl : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Discover Fragment"
    }
    val text: LiveData<String> = _text
}