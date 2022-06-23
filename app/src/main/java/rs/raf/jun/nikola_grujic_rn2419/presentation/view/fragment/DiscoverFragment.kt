package rs.raf.jun.nikola_grujic_rn2419.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import rs.raf.jun.nikola_grujic_rn2419.databinding.FragmentDiscoverBinding
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.DiscoverViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.DiscoverViewModelImpl

class DiscoverFragment : Fragment() {
    private val viewModel: DiscoverViewModel = DiscoverViewModelImpl()
    private var _binding: FragmentDiscoverBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.fetchNews()
        viewModel.newsResponse.observe(viewLifecycleOwner) { response ->
            if (response != null && response.isNotEmpty()) {
                Log.d("RESPONSE", response.toString())
            }
            else Toast.makeText(context, "Request to the server failed!",
                Toast.LENGTH_LONG).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}