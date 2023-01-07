package com.snehal.android.cryptocurrencychallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snehal.android.cryptocurrencychallenge.data.CoincapService
import com.snehal.android.cryptocurrencychallenge.data.model.assets.AssetsApiData
import com.snehal.android.cryptocurrencychallenge.data.model.markets.MarketData
import com.snehal.android.cryptocurrencychallenge.data.model.markets.MarketsApiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    val api: CoincapService
) : ViewModel() {
    val assetApiData = MutableLiveData<AssetsApiData>()
    val marketApiData = MutableLiveData<MarketsApiData>()

    fun getAssetsData() {
        viewModelScope.launch {
            try {
                val data = api.getAssets()
                assetApiData.postValue(data)
            } catch (ex: Exception) {
                ex.stackTrace
            }
        }
    }

    fun getMarketData(baseId: String?) {
        viewModelScope.launch {
            try {
                val data = api.getMarkets(baseId)
                marketApiData.postValue(data)
            } catch (ex: Exception) {
                ex.stackTrace
            }
        }
    }

    fun getDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        return format.format(date)
    }

    fun filterUiData(marketData: List<MarketData>?): MarketData? {
        return marketData
            ?.filter { it.volumeUsd24Hr != null }
            ?.maxByOrNull { it.volumeUsd24Hr.toString() }

    }
}