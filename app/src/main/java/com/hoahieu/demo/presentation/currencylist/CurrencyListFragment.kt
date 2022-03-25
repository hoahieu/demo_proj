package com.hoahieu.demo.presentation.currencylist

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.hoahieu.demo.R
import com.hoahieu.demo.databinding.FragmentCurrencylistBinding
import com.hoahieu.demo.presentation.model.CurrencyUIModel
import com.hoahieu.demo.utils.DEBOUNCE_DELAY
import com.hoahieu.demo.utils.safeLaunch
import com.hoahieu.demo.utils.setDebounceClickListener
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.viewmodel.ext.android.viewModel


class CurrencyListFragment : Fragment() {
    private var _binding: FragmentCurrencylistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyListViewModel by viewModel()
    private val itemOpenFlow = MutableSharedFlow<CurrencyUIModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrencylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        val adapter = CurrencyListAdapter(::openCurrency)
        binding.currencyList.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayout.VERTICAL)
        dividerItemDecoration.setDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.list_divider)))
        binding.currencyList.addItemDecoration(dividerItemDecoration)
        binding.fetchButton.setDebounceClickListener { viewModel.fetch() }
        binding.sortButton.setOnClickListener { viewModel.rotateSortingMode() }

        safeLaunch {
            itemOpenFlow
                .debounce(DEBOUNCE_DELAY)
                .collect { uiModel ->
                    Toast.makeText(requireContext(), "${uiModel.id} clicked", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun initViewModel() {
        safeLaunch { viewModel.currencyListFlow.collect(::displayCurrencyList) }
        safeLaunch { viewModel.sortingFlow.collect(::displaySortMode) }
    }

    private fun openCurrency(uiModel: CurrencyUIModel) = safeLaunch { itemOpenFlow.emit(uiModel) }

    private fun displaySortMode(sortingMode: SortingMode) {
        when (sortingMode) {
            SortingMode.ASCENDING -> binding.sortMode.setText(R.string.sorting_ascending)
            SortingMode.DESCENDING -> binding.sortMode.setText(R.string.sorting_descending)
            SortingMode.RANDOM -> binding.sortMode.setText(R.string.sorting_random)
        }
    }

    private fun displayCurrencyList(list: List<CurrencyUIModel>) {
        (binding.currencyList.adapter as? CurrencyListAdapter)?.submitList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}