package com.snehal.android.cryptocurrencychallenge.data

import com.snehal.android.cryptocurrencychallenge.data.model.PhotosData
import retrofit2.http.GET
import retrofit2.http.Query

const val PHOTOS_ENDPOINT_HOST = "https://jsonplaceholder.typicode.com"

interface CoincapService {

    @GET("/photos")
    suspend fun getPhotos(): List<PhotosData>
}