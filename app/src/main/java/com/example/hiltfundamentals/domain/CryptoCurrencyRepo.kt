package com.example.hiltfundamentals.domain

import com.example.hiltfundamentals.data.Cryptocurrency

interface CryptoCurrencyRepo {

    fun getCryptoCurrency():List<Cryptocurrency>

}