package com.hoahieu.demo.data.di

import com.hoahieu.demo.data.CurrencyRepoImpl
import com.hoahieu.demo.data.database.AppDatabase
import com.hoahieu.demo.data.database.createRoomDatabase
import com.hoahieu.demo.data.model.CurrencyEntity
import com.hoahieu.demo.data.model.mapper.CurrencyDataMapper
import com.hoahieu.demo.data.model.mapper.DataMapper
import com.hoahieu.demo.domain.CurrencyRepo
import com.hoahieu.demo.domain.model.Currency
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val NAMED_CURRENCY_ENTITY_MAPPER = "named_currency_entity_mapper"
val dataModule = module {
    // AppDatabase
    factory { createRoomDatabase(androidApplication()) }
    factory { get<AppDatabase>().currencyDao() }
    single<DataMapper<CurrencyEntity, Currency>>(named(NAMED_CURRENCY_ENTITY_MAPPER)) { CurrencyDataMapper() }
    single<CurrencyRepo> { CurrencyRepoImpl(get(), get(named(NAMED_CURRENCY_ENTITY_MAPPER))) }
}