package com.snehal.android.photosapp.data

import com.snehal.android.photosapp.data.model.PhotosData
import retrofit2.http.GET

const val PHOTOS_ENDPOINT_HOST = "https://jsonplaceholder.typicode.com"

interface CoincapService {

    @GET("/photos")
    suspend fun getPhotos(): List<PhotosData>
}