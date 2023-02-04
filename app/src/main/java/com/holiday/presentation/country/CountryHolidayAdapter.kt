package com.holiday.presentation.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holiday.databinding.ItemHolidayBinding
import com.holiday.domain.model.HolidaysModel
import com.holiday.extension.DateConstants
import com.holiday.extension.dateFormatter

class CountryHolidayAdapter :
    ListAdapter<HolidaysModel, CountryHolidayAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHolidayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(holiday = currentList[position])
    }

    inner class ViewHolder(
        private val binding: ItemHolidayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(holiday: HolidaysModel) {
            binding.apply {
                holidayName.text = holiday.name
                holidayLocalName.text = holiday.localName
                holidayDate.text = holiday.date?.dateFormatter(format = DateConstants.DAY_DMY_HYPHEN)

                globalState.isVisible = holiday.global
                fixedState.isVisible = holiday.fixed
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<HolidaysModel>() {
            override fun areItemsTheSame(
                oldItem: HolidaysModel,
                newItem: HolidaysModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: HolidaysModel,
                newItem: HolidaysModel
            ): Boolean = oldItem == newItem
        }
    }
}
