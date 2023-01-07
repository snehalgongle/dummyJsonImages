package com.snehal.android.cryptocurrencychallenge.ui.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snehal.android.cryptocurrencychallenge.data.model.assets.AssetData
import com.snehal.android.cryptocurrencychallenge.databinding.AssetItemBinding

class AssetsAdapter(private val assetItems: List<AssetData>, private val listener: OnClickListener) :
        RecyclerView.Adapter<AssetItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AssetItemViewHolder(
                AssetItemBinding.inflate(
                        inflater,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: AssetItemViewHolder, position: Int) {
        holder.bind(assetItems[position],listener = listener)
    }

    override fun getItemCount() = assetItems.size

    class OnClickListener(val clickListener: (id: String) -> Unit) {
        fun onClick(id: String) = clickListener(id)
    }
}

class AssetItemViewHolder(private val binding: AssetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(asset: AssetData, listener: AssetsAdapter.OnClickListener) {
        binding.textViewAssetCode.text = asset.symbol
        binding.textViewAssetName.text = asset.name
        binding.textViewAssetPrice.text = asset.priceUsd
        itemView.setOnClickListener {
            asset.id?.let { value -> listener.onClick(value) }
        }
    }
}
