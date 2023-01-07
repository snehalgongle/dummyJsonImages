package com.snehal.android.cryptocurrencychallenge.data

import com.snehal.android.cryptocurrencychallenge.data.model.assets.AssetsApiData
import com.snehal.android.cryptocurrencychallenge.data.model.markets.MarketsApiData
import retrofit2.http.GET
import retrofit2.http.Query

const val CAPCOIN_ENDPOINT_HOST = "https://api.coincap.io/"

interface CoincapService {

    @GET("/v2/assets")
    suspend fun getAssets(): AssetsApiData

    @GET("/v2/markets")
    suspend fun getMarkets(@Query("baseId") baseId: String?): MarketsApiData
}