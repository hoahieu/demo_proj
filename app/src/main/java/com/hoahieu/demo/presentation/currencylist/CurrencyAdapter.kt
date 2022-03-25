package com.hoahieu.demo.presentation.currencylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hoahieu.demo.databinding.ItemCurrencyBinding
import com.hoahieu.demo.presentation.model.CurrencyUIModel

class CurrencyListAdapter(var clickCallback: ((CurrencyUIModel) -> Unit)? = null) :
    ListAdapter<CurrencyUIModel, CurrencyViewHolder>(currencyDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val itemBinding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindItem(getItem(position), clickCallback)
    }
}

val currencyDiffCallback = object : DiffUtil.ItemCallback<CurrencyUIModel>() {
    override fun areItemsTheSame(oldItem: CurrencyUIModel, newItem: CurrencyUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyUIModel, newItem: CurrencyUIModel): Boolean {
        return oldItem.name == newItem.name && oldItem.smallIconRes == newItem.smallIconRes
    }
}