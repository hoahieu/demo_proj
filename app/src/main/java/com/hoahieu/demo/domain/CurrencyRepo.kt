package com.hoahieu.demo.domain

import com.hoahieu.demo.domain.model.Currency

interface CurrencyRepo {
    suspend fun getCurrencies(): List<Currency>
}