package com.holiday.presentation.country_select

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.holiday.databinding.BottomSheetCountryBinding
import com.holiday.domain.model.CountryModel
import com.holiday.extension.setNullableAdapter
import com.holiday.util.SHEET_HEIGHT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDialogFragment(
    private val countryCallBack: (CountryModel) -> (Unit)
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountryViewModel by viewModels()

    /**
     * This function makes BottomSheetDialogFragment full screen and without collapsed state
     * For some reason this doesn't work without the params.height
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnShowListener {
            val bottomSheet =
                dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val params = bottomSheet.layoutParams
            val height: Int = Resources.getSystem().displayMetrics.heightPixels
            val maxHeight = (height * SHEET_HEIGHT).toInt()
            params.height = maxHeight
            bottomSheet.layoutParams = params

            val behaviour = BottomSheetBehavior.from(bottomSheet)

            behaviour.apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                skipCollapsed = true
                isDraggable = false
            }
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolBar()
        setObservers()
        setupCountriesList()

        viewModel.loadAllCountries()
    }

    private fun setupToolBar() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
    }

    private fun setObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CountryUIState.Error -> {
                    println("@@@ ${state.message}")
                }
                is CountryUIState.Country -> {
                    renderCountries(countries = state.countries)
                }
                is CountryUIState.Loading -> {
                    println("@@@ ${state.isLoading}")
                }
            }
        }
    }

    private fun setupCountriesList() {
        binding.apply {
            countriesList.setNullableAdapter(adapter = countryAdapter)
        }
    }

    private val countryAdapter: CountryAdapter by lazy {
        CountryAdapter(
            countryCode = "AD",
            countryCallBack = { countryModel ->
                countryCallBack.invoke(countryModel)
                dismiss()
            }
        )
    }

    private fun renderCountries(countries: List<CountryModel>) {
        countryAdapter.submitList(countries)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
