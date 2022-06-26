package rs.raf.jun.nikola_grujic_rn2419.presentation.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.databinding.FragmentPortfolioBinding
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.PortfolioViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.PortfolioViewModelFactory
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.PortfolioViewModelImpl

class PortfolioFragment : Fragment() {
    private lateinit var viewModel: PortfolioViewModel
    private var _binding: FragmentPortfolioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()

        return root
    }

    private fun init() {
        val viewModelFactory = PortfolioViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory)[
                PortfolioViewModelImpl::class.java
        ]

        val balanceTv: TextView = binding.root.findViewById(R.id.balanceTv)
        val portfolioTv: TextView = binding.root.findViewById(R.id.portfolioTv)

        viewModel.getAccountInfo(getUsername())
        viewModel.accountResponse.observe(viewLifecycleOwner) { accountInfo ->
            if (accountInfo != null) {
                balanceTv.text = "Account balance: " + accountInfo.balance.toString()
                portfolioTv.text = "Portfolio value: " + accountInfo.portfolio.toString()
            }
        }
    }

    private fun getUsername(): String {
        val sp: SharedPreferences = requireActivity().getSharedPreferences(
            "userInfo",
            AppCompatActivity.MODE_PRIVATE
        )
        return sp.getString("username", "")!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}