package com.hoahieu.demo.presentation.currencylist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hoahieu.demo.databinding.ItemCurrencyBinding
import com.hoahieu.demo.presentation.model.CurrencyUIModel

class CurrencyViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindItem(model: CurrencyUIModel, clickCallback: ((CurrencyUIModel) -> Unit)?) {
        model.smallIconRes?.let {
            binding.currencyIcon.visibility = View.VISIBLE
            binding.currencyTextIcon.visibility = View.GONE
            binding.currencyIcon.setImageResource(it)
        } ?: run {
            binding.currencyIcon.visibility = View.GONE
            binding.currencyTextIcon.visibility = View.VISIBLE
            binding.currencyTextIcon.text = model.symbol.subSequence(0, 1)
        }
        binding.currencyName.text = model.name
        binding.currencyCode.text = model.symbol

        clickCallback?.let { callback ->
            binding.container.setOnClickListener { callback(model) }
        }
    }
}