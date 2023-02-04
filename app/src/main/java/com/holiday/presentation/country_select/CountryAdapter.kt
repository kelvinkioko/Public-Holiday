package com.holiday.presentation.country_select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holiday.databinding.ItemCountryBinding
import com.holiday.domain.model.CountryModel

class CountryAdapter(
    val countryCode: String,
    val countryCallBack: (CountryModel) -> (Unit)
) : ListAdapter<CountryModel, CountryAdapter.ViewHolder>(DIFF_UTIL) {

    private var selectedCountryCode = countryCode
    private var selectedCountryPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(country = currentList[position], position = position)
    }

    inner class ViewHolder(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryModel, position: Int) {
            binding.apply {
                countryName.text = country.commonName
                countryCode.text = country.countryCode

                if (country.countryCode == selectedCountryCode) {
                    countryCheck.isChecked = true
                    selectedCountryPosition = position
                }

                itemView.setOnClickListener {
                    selectedCountryCode = country.countryCode
                    notifyItemChanged(selectedCountryPosition)
                    selectedCountryPosition = position
                    notifyItemChanged(selectedCountryPosition)

                    countryCallBack.invoke(country)
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<CountryModel>() {
            override fun areItemsTheSame(
                oldItem: CountryModel,
                newItem: CountryModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: CountryModel,
                newItem: CountryModel
            ): Boolean = oldItem == newItem
        }
    }
}
