package com.snehal.android.photosapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.snehal.android.photosapp.data.CoincapService
import com.snehal.android.photosapp.data.model.PhotosData
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
    private lateinit var apiPhotosObserver: Observer<List<PhotosData>>

    @Test
    fun getAssetApiData() {
        testCoroutineRule.runBlockingTest {
            doReturn(getMockAssetData())
                .`when`(coincapService)
                .getPhotos()

            val viewModel = ViewModel(coincapService)
            viewModel.apiData.observeForever(apiPhotosObserver)
            viewModel.getPhotosData()

            verify(coincapService).getPhotos()
        }
    }



    private fun getMockAssetData(): List<PhotosData> {
        return listOf(
            PhotosData(id = 1,
                albumId = 1,
                title ="accusamus beatae ad facilis cum similique qui sunt",
                url ="https://via.placeholder.com/600/92c952" ,
                thumbnailUrl = "https://via.placeholder.com/150/92c952"),
            PhotosData(id = 2,
                albumId = 1,
                title ="reprehenderit est deserunt velit ipsam",
                url = "https://via.placeholder.com/600/771796" ,
                thumbnailUrl = "https://via.placeholder.com/150/771796")
        )
    }
}