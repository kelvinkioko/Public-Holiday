package com.holiday.presentation.year_picker

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.holiday.databinding.BottomSheetYearBinding
import com.holiday.util.HALF_SHEET_HEIGHT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YearDialogFragment(
    private val selectedYear: Int = 1970,
    private val yearCallBack: (Int) -> (Unit)
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetYearBinding? = null
    private val binding get() = _binding!!

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
            val maxHeight = (height * HALF_SHEET_HEIGHT).toInt()
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
        _binding = BottomSheetYearBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolBar()
        setClickListener()

        binding.apply {
            yearPicker.maxValue = 2023
            yearPicker.minValue = 1970

            yearPicker.value = selectedYear

            yearPicker.setOnValueChangedListener { _, _, newYear ->
                yearConfirm.isEnabled = (newYear != selectedYear)
            }
        }
    }

    private fun setClickListener() {
        binding.apply {
            yearConfirm.setOnClickListener {
                yearCallBack.invoke(yearPicker.value)
                dismiss()
            }
        }
    }

    private fun setupToolBar() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
