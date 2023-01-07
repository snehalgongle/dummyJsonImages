package com.snehal.android.cryptocurrencychallenge.ui.market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.snehal.android.cryptocurrencychallenge.ViewModel
import com.snehal.android.cryptocurrencychallenge.data.model.markets.MarketData
import com.snehal.android.cryptocurrencychallenge.databinding.FragmentMarketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMarketData(arguments?.getString("data"))
        viewModel.marketApiData.observe(viewLifecycleOwner, { data ->
            filterUiData(data.marketData)
        })
    }

    private fun filterUiData(marketData: List<MarketData>?) {
        val maxMarketData = marketData
            ?.filter { it.volumeUsd24Hr != null }
            ?.maxByOrNull { it.volumeUsd24Hr.toString() }

        viewModel.filterUiData(marketData).let {
            binding.textViewExchangeId.text = maxMarketData?.exchangeId
            binding.textViewRank.text = maxMarketData?.rank
            binding.textViewPrice.text = maxMarketData?.priceUsd
            binding.textViewDate.text = maxMarketData?.updated?.let { viewModel.getDate(it) }
        }

    }

    companion object {
        fun newInstance(): MarketFragment {
            return MarketFragment()
        }
    }
}