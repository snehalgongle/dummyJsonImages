package com.snehal.android.photosapp.data

import com.snehal.android.photosapp.data.model.Data
import com.snehal.android.photosapp.data.model.Login
import com.snehal.android.photosapp.data.model.Product
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

const val PHOTOS_ENDPOINT_HOST = "https://dummyjson.com"

interface CoincapService {

    @GET("/products?limit=10&skip=10")
    suspend fun getPhotos(): Data

    @GET("/products/{id}/")
    suspend fun getDescData(@Path(value = "id")id: Int): Product

    @GET("/products/search")
    suspend fun getSearch(@Query("q") title: String?): Data

    @POST("/auth/login")
    suspend fun getLogin(@Query("username") user: String,@Query("password")pass :String):Login
}