package com.example.currencyconversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconversion.models.CurrencyData


class MainViewModel: ViewModel() {
    private val data = MutableLiveData<CurrencyData>()

    fun setData(value: CurrencyData) {
        data.value = value
    }

    fun getData(): LiveData<CurrencyData>? {
        return data
    }
}