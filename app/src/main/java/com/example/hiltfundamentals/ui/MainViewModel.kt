package com.example.hiltfundamentals.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hiltfundamentals.domain.CryptoCurrencyRepo
import com.example.hiltfundamentals.data.Cryptocurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cryptoCurrencyRepo: CryptoCurrencyRepo) :ViewModel(){

    private val cryptocurrencyEmitter = MutableLiveData<List<Cryptocurrency>>()
    val cryptocurrency : LiveData<List<Cryptocurrency>> = cryptocurrencyEmitter
    init {
        loadCryptoCurrency()
    }

    private fun loadCryptoCurrency() {
        cryptocurrencyEmitter.value = cryptoCurrencyRepo.getCryptoCurrency()
    }


}