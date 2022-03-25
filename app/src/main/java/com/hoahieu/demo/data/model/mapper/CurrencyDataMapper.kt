package com.hoahieu.demo.data.model.mapper

import com.hoahieu.demo.data.model.CurrencyEntity
import com.hoahieu.demo.domain.model.Currency

class CurrencyDataMapper : DataMapper<CurrencyEntity, Currency>{
    override fun mapFromEntity(entity: CurrencyEntity) = Currency(
        id = entity.id,
        name = entity.name,
        symbol = entity.symbol
    )
}