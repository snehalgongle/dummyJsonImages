package com.snehal.android.photosapp.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snehal.android.photosapp.data.model.Product
import com.snehal.android.photosapp.databinding.AssetItemBinding
import com.squareup.picasso.Picasso

class AssetsAdapter(private val productList: List<Product>, private val listener: OnClickListener) :
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
        holder.bind(productList[position], listener = listener)
    }

    override fun getItemCount() = productList.size

    class OnClickListener(val clickListener: (id: Int,category:String,description:String,title:String) -> Unit) {
        fun onClick(id: Int,category:String, description: String, title:String) = clickListener(id,category,description,title)
    }
}

class AssetItemViewHolder(private val binding: AssetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(productData: Product, listener: AssetsAdapter.OnClickListener) {
        binding.textViewTitle.text = productData.title
        binding.textViewDesc.text = productData.description
        Picasso.get()
            .load(productData.thumbnail)
            .into(binding.imgPhoto)
        itemView.setOnClickListener {
            listener.onClick(productData.id,productData.category,productData.description,productData.title)
        }
    }
}
