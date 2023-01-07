package com.snehal.android.cryptocurrencychallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.snehal.android.cryptocurrencychallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home_activity)

    }

}