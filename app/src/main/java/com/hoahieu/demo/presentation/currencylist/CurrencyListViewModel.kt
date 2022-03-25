package com.hoahieu.demo.presentation.currencylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoahieu.demo.domain.model.Currency
import com.hoahieu.demo.domain.usecase.GetCurrencyListUseCase
import com.hoahieu.demo.domain.usecase.execute
import com.hoahieu.demo.presentation.mapper.UIMapper
import com.hoahieu.demo.presentation.model.CurrencyUIModel
import com.hoahieu.demo.utils.DEBOUNCE_DELAY
import com.hoahieu.demo.utils.DispatchFactory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    private val dispatchFactory: DispatchFactory,
    private val getCurrencyListUseCase: GetCurrencyListUseCase,
    private val uiMapper: UIMapper<Currency, CurrencyUIModel>
) : ViewModel() {

    private val _currencyListFlow = MutableSharedFlow<List<CurrencyUIModel>>(replay = 1)
    val currencyListFlow = _currencyListFlow.asSharedFlow()
    private val _sortingFlow = MutableStateFlow<SortingMode>(SortingMode.ASCENDING)
    val sortingFlow = _sortingFlow.asStateFlow()

    init {
        viewModelScope.launch(dispatchFactory.default()) {
            sortingFlow.debounce(DEBOUNCE_DELAY)
                .collect { sortingMode ->
                    currencyListFlow.replayCache.lastOrNull()?.run {
                        _currencyListFlow.tryEmit(sortBy(sortingMode))
                    }
                }
        }
    }

    fun fetch() {
        viewModelScope.launch(dispatchFactory.io()) {
            getCurrencyListUseCase
                .execute()
                .map { it.map(uiMapper::mapFromDomain) }
                .map {
                    it.sortBy(sortingFlow.value)
                }
                .collect(_currencyListFlow::emit)
        }
    }

    fun rotateSortingMode() {
        viewModelScope.launch {
            _sortingFlow.emit(_sortingFlow.value.rotate())
        }
    }

    private fun List<CurrencyUIModel>.sortBy(sortingMode: SortingMode) = when (sortingMode) {
        SortingMode.ASCENDING -> this.sortedBy { it.symbol }
        SortingMode.DESCENDING -> this.sortedByDescending { it.symbol }
        SortingMode.RANDOM -> this.shuffled()
    }
}