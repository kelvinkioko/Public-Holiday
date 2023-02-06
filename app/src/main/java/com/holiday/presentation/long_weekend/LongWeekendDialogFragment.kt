package com.holiday.presentation.long_weekend

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.holiday.databinding.BottomSheetLongWeekendBinding
import com.holiday.domain.model.LongWeekendModel
import com.holiday.extension.observeState
import com.holiday.extension.setLottiePadding
import com.holiday.extension.setNullableAdapter
import com.holiday.util.SHEET_HEIGHT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LongWeekendDialogFragment(
    private val year: Int,
    private val countryCode: String
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLongWeekendBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LongWeekendViewModel by viewModels()

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
        _binding = BottomSheetLongWeekendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolBar()
        setObservers()
        setupCountriesList()

        binding.loader.setLottiePadding()

        viewModel.loadLongWeekends(year = year, countryCode = countryCode)
    }

    private fun setupToolBar() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
    }

    private fun setObservers() {
        viewModel.uiState.observeState(this, Lifecycle.State.STARTED) { state ->
            when (state) {
                is LongWeekendUIState.Error -> {
                    setEmptyState()
                }
                is LongWeekendUIState.LongWeekend -> {
                    renderWeekends(weekends = state.weekends)
                }
                is LongWeekendUIState.Loading -> {
                    setLoaderState(isLoading = state.isLoading)
                }
            }
        }
    }

    private fun setupCountriesList() {
        binding.apply {
            longWeekendList.setNullableAdapter(adapter = countryAdapter)
        }
    }

    private val countryAdapter: LongWeekendAdapter by lazy { LongWeekendAdapter() }

    private fun renderWeekends(weekends: List<LongWeekendModel>) {
        setEmptyState(isVisible = weekends.isEmpty())
        countryAdapter.submitList(weekends)
    }

    private fun setLoaderState(isLoading: Boolean = false) {
        binding.apply {
            loaderGroup.isVisible = isLoading
            longWeekendList.isGone = isLoading
        }
    }

    private fun setEmptyState(isVisible: Boolean = true) {
        binding.apply {
            longWeekendList.isGone = isVisible
            emptyState.apply {
                root.isVisible = isVisible
                emptyMessage.text = "No long weekends available."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
