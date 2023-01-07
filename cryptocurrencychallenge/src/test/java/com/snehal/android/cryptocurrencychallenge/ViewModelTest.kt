package com.snehal.android.cryptocurrencychallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.snehal.android.cryptocurrencychallenge.data.CoincapService
import com.snehal.android.cryptocurrencychallenge.data.model.assets.AssetData
import com.snehal.android.cryptocurrencychallenge.data.model.assets.AssetsApiData
import com.snehal.android.cryptocurrencychallenge.data.model.markets.MarketData
import com.snehal.android.cryptocurrencychallenge.data.model.markets.MarketsApiData
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var coincapService: CoincapService

    @Mock
    private lateinit var apiUsersObserver: Observer<AssetsApiData>

    @Test
    fun getAssetApiData() {
        testCoroutineRule.runBlockingTest {
            doReturn(getMockAssetData())
                .`when`(coincapService)
                .getAssets()

            val viewModel = ViewModel(coincapService)
            viewModel.assetApiData.observeForever(apiUsersObserver)
            viewModel.getAssetsData()

            verify(coincapService).getAssets()
            assertEquals(2, coincapService.getAssets().assetData?.size)
        }
    }

    @Test
    fun getMarketApiData() {
        testCoroutineRule.runBlockingTest {
            doReturn(getMockMarketData())
                .`when`(coincapService)
                .getMarkets("bitcoin")
            val viewModel = ViewModel(coincapService)
            viewModel.assetApiData.observeForever(apiUsersObserver)
            viewModel.getMarketData("bitcoin")
            verify(coincapService).getMarkets("bitcoin")
            assertEquals(2, coincapService.getMarkets("bitcoin").marketData?.size)
        }
    }

    @Test
    fun testFilterUiData() {
        testCoroutineRule.runBlockingTest {
            doReturn(getMockMarketData())
                .`when`(coincapService)
                .getMarkets("bitcoin")
            val viewModel = ViewModel(coincapService)
            viewModel.assetApiData.observeForever(apiUsersObserver)
            viewModel.getMarketData("bitcoin")
            verify(coincapService).getMarkets("bitcoin")
            val maxvalue = viewModel.filterUiData(getMockMarketData()?.marketData)?.volumeUsd24Hr
            assertEquals("2927959461.1750323310959460",maxvalue)
        }
    }

    @Test
    fun testGetDate() {
        val viewModel = ViewModel(coincapService)
        val formattedDate = viewModel.getDate(123456788)
        assert(formattedDate == "02/01/1970")
    }

    private fun getMockAssetData(): AssetsApiData {
        return AssetsApiData(
            assetData =
            listOf(
                AssetData(
                    id = "bitcoin", rank = "1",
                    symbol = "BTC",
                    name = "Bitcoin",
                    supply = "17193925.0000000000000000",
                    maxSupply = "21000000.0000000000000000",
                    marketCapUsd = "119150835874.4699281625807300",
                    volumeUsd24Hr = "2927959461.1750323310959460",
                    priceUsd = "6929.8217756835584756",
                    changePercent24Hr = "-0.8101417214350335",
                    vwap24Hr = "7175.0663247679233209",
                    explorer = ""
                ),
                AssetData(
                    id = "bitcoin", rank = "1",
                    symbol = "BTC",
                    name = "Bitcoin",
                    supply = "17193925.0000000000000000",
                    maxSupply = "21000000.0000000000000000",
                    marketCapUsd = "119150835874.4699281625807300",
                    volumeUsd24Hr = "3027959461.1750323310959460",
                    priceUsd = "6929.8217756835584756",
                    changePercent24Hr = "-0.8101417214350335",
                    vwap24Hr = "7175.0663247679233209",
                    explorer = ""
                )
            ), timestamp = 12344
        )
    }

    private fun getMockMarketData(): MarketsApiData? {
        return MarketsApiData(
            marketData =
            listOf(
                MarketData(
                    exchangeId="Binance",
                    baseId= "bitcoin",
                    quoteId= "tether",
                    baseSymbol= "BTC",
                    quoteSymbol= "USDT",
                    volumeUsd24Hr= "2927959461.1750323310959460",
                    priceUsd="6263.8645034633024446",
                    percentExchangeVolume = "",
                    priceQuote = "999",
                    rank = "",
                    tradesCount24Hr = "",
                    updated = 34543
                ),
                MarketData(
                    exchangeId="Binance",
                    baseId= "bitcoin",
                    quoteId= "tether",
                    baseSymbol= "BTC",
                    quoteSymbol= "USDT",
                    volumeUsd24Hr= "277775213.1923032624064566",
                    priceUsd="6263.8645034633024446",
                    percentExchangeVolume = "",
                    priceQuote = "111",
                    rank = "",
                    tradesCount24Hr = "",
                    updated = 34543
                )
            ), timestamp = 12344
        )
    }
}