package com.holiday.presentation.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.holiday.databinding.FragmentWorldHolidayBinding
import com.holiday.domain.model.HolidaysModel
import com.holiday.extension.setLottiePadding
import com.holiday.extension.setNullableAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorldHolidayFragment : Fragment() {

    private var _binding: FragmentWorldHolidayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorldHolidayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorldHolidayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setUpList()
        setInputListeners()

        binding.loader.setLottiePadding()

        viewModel.loadWorldHolidays()
    }

    private fun setObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WorldHolidayUIState.Error -> {
                    setEmptyState()
                }
                is WorldHolidayUIState.Holidays -> {
                    renderPasswords(holidays = state.holidays)
                }
                is WorldHolidayUIState.Loading -> {
                    setLoaderState(isLoading = state.isLoading)
                }
            }
        }
    }

    private val worldHolidayAdapter: WorldHolidayAdapter by lazy { WorldHolidayAdapter() }

    private fun setUpList() {
        binding.holidaysList.setNullableAdapter(worldHolidayAdapter)
    }

    private fun renderPasswords(holidays: List<HolidaysModel>) {
        setEmptyState(isVisible = holidays.isEmpty())
        worldHolidayAdapter.submitList(holidays)
    }

    private fun setLoaderState(isLoading: Boolean = false) {
        binding.apply {
            loaderGroup.isVisible = isLoading
            holidaysList.isGone = isLoading
            emptyState.root.isGone = true
        }
    }

    private fun setEmptyState(isVisible: Boolean = true) {
        binding.apply {
            holidaysList.isGone = isVisible
            emptyState.apply {
                root.isVisible = isVisible
                emptyMessage.text = "No holidays available."
            }
        }
    }

    private fun setInputListeners() {
        binding.searchHolidayInput.editText?.doAfterTextChanged { searchQuery ->
            if (searchQuery != null && searchQuery.isNotEmpty())
                viewModel.filterHolidays(searchQuery = searchQuery.toString())
            else
                viewModel.filterHolidays(searchQuery = "")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
