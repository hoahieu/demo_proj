package com.hoahieu.demo.presentation.di

import com.hoahieu.demo.domain.model.Currency
import com.hoahieu.demo.domain.usecase.GetCurrencyListUseCase
import com.hoahieu.demo.presentation.currencylist.CurrencyListViewModel
import com.hoahieu.demo.presentation.mapper.CurrencyUIModelMapper
import com.hoahieu.demo.presentation.mapper.UIMapper
import com.hoahieu.demo.presentation.model.CurrencyUIModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MAPPER_CURRENCY_UI_MODEL = "mapper_currency_ui_model"

val uiModule = module {
    factory { GetCurrencyListUseCase(get()) }
    factory<UIMapper<Currency, CurrencyUIModel>>(named(MAPPER_CURRENCY_UI_MODEL)) { CurrencyUIModelMapper() }

    viewModel {
        CurrencyListViewModel(
            dispatchFactory = get(),
            getCurrencyListUseCase = get(),
            uiMapper = get(named(MAPPER_CURRENCY_UI_MODEL))
        )
    }
}