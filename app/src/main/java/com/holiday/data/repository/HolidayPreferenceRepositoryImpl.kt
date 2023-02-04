package com.holiday.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.holiday.domain.repository.HolidayPreferenceRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HolidayPreferenceRepositoryImpl @Inject constructor(
    private val holidayPreferences: DataStore<Preferences>
) : HolidayPreferenceRepository {

    override suspend fun setCountry(countryCode: String) {
        Result.runCatching {
            holidayPreferences.edit { preferences ->
                preferences[COUNTRY_CODE] = countryCode
            }
        }
    }

    override suspend fun getCountry(): Result<String> {
        return Result.runCatching {
            val flow = holidayPreferences.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[COUNTRY_CODE]
                }
            val countryCode = flow.firstOrNull() ?: ""
            countryCode
        }
    }

    override suspend fun setYear(year: Int) {
        Result.runCatching {
            holidayPreferences.edit { preferences ->
                preferences[YEAR_CODE] = year
            }
        }
    }

    override suspend fun getYear(): Result<Int> {
        return Result.runCatching {
            val flow = holidayPreferences.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[YEAR_CODE]
                }
            val countryCode = flow.firstOrNull() ?: 0
            countryCode
        }
    }

    private companion object {
        val COUNTRY_CODE = stringPreferencesKey(name = "country_code")
        val YEAR_CODE = intPreferencesKey(name = "year_code")
    }
}
