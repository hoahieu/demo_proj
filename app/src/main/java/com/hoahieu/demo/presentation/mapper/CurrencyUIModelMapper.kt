package com.hoahieu.demo.presentation.mapper

import com.hoahieu.demo.R
import com.hoahieu.demo.domain.model.Currency
import com.hoahieu.demo.presentation.model.CurrencyUIModel

class CurrencyUIModelMapper : UIMapper<Currency, CurrencyUIModel> {
    override fun mapFromDomain(model: Currency) = CurrencyUIModel(
        id = model.id,
        smallIconRes = getSmallIconRes(model.symbol),
        name = model.name,
        symbol = model.symbol
    )

    private fun getSmallIconRes(code: String): Int? {
        return when (code) {
            "BTC" -> R.drawable.ic_btc
            "ETH" -> R.drawable.ic_eth
            "XRP" -> R.drawable.ic_xrp
            "BCH" -> R.drawable.ic_bch
            "LTC" -> R.drawable.ic_ltc
            "EOS" -> R.drawable.ic_eos
            "BNB" -> R.drawable.ic_bnb
            "LINK" -> R.drawable.ic_link
            "ETC" -> R.drawable.ic_etc
            "ONT" -> R.drawable.ic_ont
            "CRO" -> R.drawable.ic_cro
            "USDC" -> R.drawable.ic_usdc
            else -> null
        }
    }
}