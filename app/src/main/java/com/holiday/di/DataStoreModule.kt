package com.holiday.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.holidayDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.holiday.di"
)
