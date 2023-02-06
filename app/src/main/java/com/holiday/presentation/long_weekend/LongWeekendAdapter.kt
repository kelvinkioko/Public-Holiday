package com.holiday.presentation.long_weekend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holiday.databinding.ItemLongWeekendBinding
import com.holiday.domain.model.LongWeekendModel
import com.holiday.extension.DateConstants
import com.holiday.extension.dateFormatter

class LongWeekendAdapter : ListAdapter<LongWeekendModel, LongWeekendAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLongWeekendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weekend = currentList[position])
    }

    inner class ViewHolder(
        private val binding: ItemLongWeekendBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weekend: LongWeekendModel) {
            binding.apply {
                dayCount.text = "${weekend.dayCount} Days"
                startDate.text =
                    weekend.startDate?.dateFormatter(format = DateConstants.DAY_DMY_HYPHEN)
                endDate.text =
                    weekend.endDate?.dateFormatter(format = DateConstants.DAY_DMY_HYPHEN)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<LongWeekendModel>() {
            override fun areItemsTheSame(
                oldItem: LongWeekendModel,
                newItem: LongWeekendModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: LongWeekendModel,
                newItem: LongWeekendModel
            ): Boolean = oldItem == newItem
        }
    }
}
