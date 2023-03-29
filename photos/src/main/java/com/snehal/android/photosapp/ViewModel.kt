package com.snehal.android.photosapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snehal.android.photosapp.data.CoincapService
import com.snehal.android.photosapp.data.model.Data
import com.snehal.android.photosapp.data.model.Login
import com.snehal.android.photosapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    val api: CoincapService
) : ViewModel() {
    val apiData = MutableLiveData<Data>()
    val productData = MutableLiveData<Product>()
    val loginData =MutableLiveData<Login>()
    val loginErrorData =MutableLiveData<String>()

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


    fun getDescData(id:Int) {
        viewModelScope.launch {
            try {
                val data = api.getDescData(id)
                productData.postValue(data)
            } catch (ex: Exception) {
                ex.stackTrace
            }
        }
    }

    fun search(query: String?) {
        viewModelScope.launch {
            try {
                val data = api.getSearch(query)
                apiData.postValue(data)
            } catch (ex: Exception) {
                ex.stackTrace
            }
        }
    }

    fun getLogin(user:String,pass:String) {
        viewModelScope.launch {
            try {
                val data = api.getLogin(user,pass)
                if(data.isSuccessful) {
                    loginData.postValue(data.body())
                }else{
                    loginErrorData.postValue("Invalid credentials")
                }
            } catch (ex: Exception) {
                ex.stackTrace
            }
        }
    }
}