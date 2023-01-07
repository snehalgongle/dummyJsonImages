package com.snehal.android.cryptocurrencychallenge.ui.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.snehal.android.cryptocurrencychallenge.R
import com.snehal.android.cryptocurrencychallenge.ViewModel
import com.snehal.android.cryptocurrencychallenge.data.model.assets.AssetsApiData
import com.snehal.android.cryptocurrencychallenge.databinding.FragmentAssetsBinding
import com.snehal.android.cryptocurrencychallenge.ui.market.MarketFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetsFragment : Fragment() {

    private lateinit var binding: FragmentAssetsBinding

    private val viewModel: ViewModel by viewModels()
    private lateinit var assetsAdapter: AssetsAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentAssetsBinding.inflate(layoutInflater)
        viewModel.getAssetsData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.assetApiData.observe(viewLifecycleOwner, { data->
            bindListData(assets = data)
        })
    }

    private fun bindListData(assets: AssetsApiData) {
        binding.progressBarAssets.visibility = View.GONE
        if (!(assets.assetData.isNullOrEmpty())) {
            assetsAdapter = AssetsAdapter(
                assets.assetData,
                listener = AssetsAdapter.OnClickListener { id ->
                    val bundle = Bundle()
                    bundle.putString("data", id)
                    val fragment = MarketFragment.newInstance()
                    fragment.arguments = bundle
                    requireFragmentManager().beginTransaction().apply {
                        replace(R.id.fragmentContainerHome, fragment, "Market_Fragment")
                        addToBackStack("Market_Fragment")
                        commit()
                    }
                })
            binding.recyclerViewAssets.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
                this.adapter=assetsAdapter
            }
        } else {
            binding.recyclerViewAssets.visibility = View.GONE
            binding.noDataImageAssets.visibility = View.VISIBLE
        }
    }

}
