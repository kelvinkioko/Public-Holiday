package com.holiday.presentation.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.holiday.databinding.FragmentCountryHolidayBinding
import com.holiday.domain.model.HolidaysModel
import com.holiday.extension.setNullableAdapter
import com.holiday.presentation.country_select.CountryDialogFragment
import com.holiday.presentation.world.WorldHolidayAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryHolidayFragment : Fragment() {

    private var _binding: FragmentCountryHolidayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountryHolidayViewModel by viewModels()

    private var bottomSheet: BottomSheetDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryHolidayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActions()
        setObservers()
        setUpList()

        viewModel.loadHolidays()
    }

    private fun setupActions() {
        binding.apply {
            selectCountry.apply {
                optionTitle.text = "Country"
                selectedOption.text = "Kenya"
                root.setOnClickListener {
                    bottomSheet = CountryDialogFragment(
                        countryCallBack = { countryModel ->
                        }
                    )
                    bottomSheet?.show(parentFragmentManager, bottomSheet?.tag ?: "")
                }
            }

            selectYear.apply {
                optionTitle.text = "Year"
                selectedOption.text = "2023"
                root.setOnClickListener {
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CountryHolidayUIState.Error -> {
                    println("@@@ ${state.message}")
                }
                is CountryHolidayUIState.Holidays -> {
                    renderHolidays(holidays = state.holidays)
                }
                is CountryHolidayUIState.Loading -> {
                    println("@@@ ${state.isLoading}")
                }
            }
        }
    }

    private val worldHolidayAdapter: WorldHolidayAdapter by lazy { WorldHolidayAdapter() }

    private fun setUpList() {
        binding.holidaysList.setNullableAdapter(worldHolidayAdapter)
    }

    private fun renderHolidays(holidays: List<HolidaysModel>) {
        worldHolidayAdapter.submitList(holidays)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
