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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.databinding.FragmentPortfolioBinding
import rs.raf.jun.nikola_grujic_rn2419.presentation.view.adapter.PortfolioRecyclerAdapter
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
        val username = getUsername()

        // account info
        viewModel.getAccountInfo(username)
        viewModel.accountResponse.observe(viewLifecycleOwner) { accountInfo ->
            if (accountInfo != null) {
                balanceTv.text = "Account balance: " + accountInfo.balance.toString()
                portfolioTv.text = "Portfolio value: " + accountInfo.portfolio.toString()
            }
            else {
                balanceTv.text = "Account balance: 10000.0"
                portfolioTv.text = "Portfolio value: 0.0"
            }
        }

        // chart
        viewModel.getPortfolioHistory(username)
        viewModel.portfolioResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                val chart: LineChart = binding.root.findViewById(R.id.portfolioChart)
                val entries = ArrayList<Entry>()
                entries.add(Entry(0.toFloat(), 0.toFloat()))

                for (p: PortfolioHistory in response)
                    entries.add(Entry(entries.size.toFloat(), p.value.toFloat()))

                val set = LineDataSet(entries, "Stock")

                val dataSet = ArrayList<ILineDataSet>()
                dataSet.add(set)
                val data = LineData(dataSet)
                chart.data = data
                chart.invalidate()
            }
        }

        // bought stocks
        viewModel.getBoughtStocks(username)
        viewModel.stocksResponse.observe(viewLifecycleOwner) { response1 ->
            if (response1 != null) {
                // fetch data for chart
                // treba fetchovati podatke za svaki kupljeni
                viewModel.fetchStock("NEMA SIMBOLA JER JEDINO POSTOJE PODACI ZA T")
                viewModel.quoteResponse.observe(viewLifecycleOwner) { response2 ->
                    val chartData = response2.chart
                    val list = response1 as ArrayList<BoughtStock>
                    val recView: RecyclerView = binding.root.findViewById(R.id.portfolioRecView)
                    val recAdapter = PortfolioRecyclerAdapter(list, chartData, requireActivity())
                    val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                        requireContext().applicationContext
                    )
                    recView.layoutManager = layoutManager
                    recView.itemAnimator = DefaultItemAnimator()
                    recView.adapter = recAdapter
                }
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

    override fun onResume() {
        super.onResume()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}