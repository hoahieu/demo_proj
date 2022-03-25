package com.hoahieu.demo.domain.usecase

import com.hoahieu.demo.domain.CurrencyRepo
import com.hoahieu.demo.domain.model.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrencyListUseCase(private val currencyRepo: CurrencyRepo) :
    FlowUseCase<Any?, List<Currency>> {
    override fun execute(parameters: Any?): Flow<List<Currency>> = flow {
        emit(currencyRepo.getCurrencies())
    }
}