package com.holiday.presentation.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.holiday.databinding.FragmentWorldHolidayBinding
import com.holiday.domain.model.HolidaysModel
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

        viewModel.loadWorldHolidays()
    }

    private fun setObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WorldHolidayUIState.Error -> {
                    println("@@@ ${state.message}")
                }
                is WorldHolidayUIState.Holidays -> {
                    renderPasswords(holidays = state.holidays)
                }
                is WorldHolidayUIState.Loading -> {
                    println("@@@ ${state.isLoading}")
                }
            }
        }
    }

    private val worldHolidayAdapter: WorldHolidayAdapter by lazy { WorldHolidayAdapter() }

    private fun setUpList() {
        binding.holidaysList.setNullableAdapter(worldHolidayAdapter)
    }

    private fun renderPasswords(holidays: List<HolidaysModel>) {
        worldHolidayAdapter.submitList(holidays)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
