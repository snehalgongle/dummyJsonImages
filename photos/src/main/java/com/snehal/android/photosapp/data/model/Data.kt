package com.snehal.android.photosapp.data.model

data class Data(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)