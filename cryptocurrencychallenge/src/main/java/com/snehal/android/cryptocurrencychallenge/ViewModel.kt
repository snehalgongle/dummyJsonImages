package com.snehal.android.cryptocurrencychallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snehal.android.cryptocurrencychallenge.data.CoincapService
import com.snehal.android.cryptocurrencychallenge.data.model.PhotosData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    val api: CoincapService
) : ViewModel() {
    val apiData = MutableLiveData<List<PhotosData>>()

    fun getPhotosData() {
        viewModelScope.launch {
            try {
                val data = api.getPhotos()
                apiData.postValue(data)
            } catch (ex: Exception) {
                ex.stackTrace
            }
        }
    }
}