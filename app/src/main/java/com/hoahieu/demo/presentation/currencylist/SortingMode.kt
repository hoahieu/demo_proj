package com.hoahieu.demo.presentation.currencylist

enum class SortingMode {
    ASCENDING,
    DESCENDING,
    RANDOM;

    fun rotate() = when (this) {
        ASCENDING -> DESCENDING
        DESCENDING -> RANDOM
        RANDOM -> ASCENDING
    }
}