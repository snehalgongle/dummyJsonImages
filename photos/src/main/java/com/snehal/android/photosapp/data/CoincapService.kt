package com.snehal.android.photosapp.data

import com.snehal.android.photosapp.data.model.Data
import com.snehal.android.photosapp.data.model.Login
import com.snehal.android.photosapp.data.model.Product
import retrofit2.Response
import retrofit2.http.*

const val PHOTOS_ENDPOINT_HOST = "https://dummyjson.com"

interface CoincapService {

    @GET("/products?limit=10&skip=10")
    suspend fun getPhotos(): Data

    @GET("/products/{id}/")
    suspend fun getDescData(@Path(value = "id")id: Int): Product

    @GET("/products/search")
    suspend fun getSearch(@Query("q") title: String?): Data

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun getLogin(
        @Field("username")  user: String,
        @Field("password") pass: String
    ): Response<Login>
}