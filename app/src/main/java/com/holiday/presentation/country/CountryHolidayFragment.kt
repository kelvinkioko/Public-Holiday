package com.holiday.presentation.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.holiday.databinding.FragmentCountryHolidayBinding
import com.holiday.domain.model.CountryModel
import com.holiday.domain.model.HolidaysModel
import com.holiday.domain.repository.HolidayPreferenceRepository
import com.holiday.extension.setNullableAdapter
import com.holiday.presentation.country_select.CountryDialogFragment
import com.holiday.presentation.year_picker.YearDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class CountryHolidayFragment : Fragment() {

    private var _binding: FragmentCountryHolidayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountryHolidayViewModel by viewModels()

    @Inject
    lateinit var holidayPreferenceRepository: HolidayPreferenceRepository

    private var bottomSheet: BottomSheetDialogFragment? = null

    private var countryModel: CountryModel? = null
    private var countryCode = ""
    private var year = 1970

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

        loadHolidays()
    }

    private fun setupActions() {
        binding.apply {
            selectCountry.apply {
                optionTitle.text = "Country"
                selectedOption.text = countryModel?.commonName ?: "Select country"
                root.setOnClickListener {
                    bottomSheet = CountryDialogFragment(
                        countryModel = countryModel,
                        countryCallBack = { countryModel ->
                            selectedOption.text = countryModel.commonName
                            this@CountryHolidayFragment.countryModel = countryModel
                            this@CountryHolidayFragment.countryCode = countryModel.countryCode

                            saveCountrySelection()
                            loadHolidays()
                        }
                    )
                    bottomSheet?.show(parentFragmentManager, bottomSheet?.tag ?: "")
                }
            }

            selectYear.apply {
                optionTitle.text = "Year"
                selectedOption.text = year.toString()
                root.setOnClickListener {
                    bottomSheet = YearDialogFragment(
                        selectedYear = this@CountryHolidayFragment.year,
                        yearCallBack = { year ->
                            selectedOption.text = year.toString()
                            this@CountryHolidayFragment.year = year

                            saveYearSelection()
                            loadHolidays()
                        }
                    )
                    bottomSheet?.show(parentFragmentManager, bottomSheet?.tag ?: "")
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
                is CountryHolidayUIState.Country -> {
                    countryModel = state.countryModel
                    setupActions()
                }
                is CountryHolidayUIState.Loading -> {
                    setLoaderState(isLoading = state.isLoading)
                }
            }
        }
    }

    private fun loadHolidays() {
        runBlocking {
            countryCode = holidayPreferenceRepository.getCountry().getOrNull() ?: ""
            year = holidayPreferenceRepository.getYear().getOrNull() ?: 1970
        }

        setupActions()

        viewModel.loadHolidays(year = year, countryCode = countryCode)
    }

    private fun saveCountrySelection() {
        runBlocking {
            holidayPreferenceRepository.setCountry(countryCode = countryCode)
        }
    }

    private fun saveYearSelection() {
        runBlocking {
            holidayPreferenceRepository.setYear(year = year)
        }
    }

    private val countryHolidayAdapter: CountryHolidayAdapter by lazy { CountryHolidayAdapter() }

    private fun setUpList() {
        binding.holidaysList.setNullableAdapter(countryHolidayAdapter)
    }

    private fun renderHolidays(holidays: List<HolidaysModel>) {
        countryHolidayAdapter.submitList(holidays)
    }

    private fun setLoaderState(isLoading: Boolean = false) {
        binding.apply {
            loaderGroup.isVisible = isLoading
            holidaysList.isGone = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
