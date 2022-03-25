package com.hoahieu.demo.data

import com.hoahieu.demo.data.dao.CurrencyDao
import com.hoahieu.demo.data.model.CurrencyEntity
import com.hoahieu.demo.data.model.mapper.DataMapper
import com.hoahieu.demo.domain.CurrencyRepo
import com.hoahieu.demo.domain.model.Currency

class CurrencyRepoImpl(
    private val currencyDao: CurrencyDao,
    private val currencyMapper: DataMapper<CurrencyEntity, Currency>,
) : CurrencyRepo {
    override suspend fun getCurrencies(): List<Currency> {
        return currencyDao.getAll().map(currencyMapper::mapFromEntity)
    }
}