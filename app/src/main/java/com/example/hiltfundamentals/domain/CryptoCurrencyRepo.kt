package com.example.hiltfundamentals.domain

import com.example.hiltfundamentals.data.Cryptocurrency
import javax.inject.Inject

interface CryptoCurrencyRepo {

    fun getCryptoCurrency():List<Cryptocurrency>
    fun addMoreCryptoCurrency(): Cryptocurrency

}