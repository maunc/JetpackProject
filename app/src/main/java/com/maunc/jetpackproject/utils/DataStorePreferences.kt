package com.maunc.jetpackproject.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@SuppressLint("StaticFieldLeak")
object DataStorePreferences {

    private lateinit var mContext: Context

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "JetPackProject"
    )

    /**
     * 初始化
     */
    fun init(
        context: Context,
    ) {
        mContext = context
    }

    /**
     *存String
     */
    suspend fun setString(
        key: String,
        value: String,
    ) {
        val stringPreferencesKey = stringPreferencesKey(key)
        mContext.dataStore.edit {
            it[stringPreferencesKey] = value
        }
    }

    /**
     * 取String
     */
    suspend fun getString(
        key: String,
        default: String = "",
    ): String {
        val stringPreferencesKey = stringPreferencesKey(key)
        val stringValueFlow = mContext.dataStore.data.map {
            it[stringPreferencesKey] ?: default
        }
        return stringValueFlow.first()
    }

    /**
     * 存Boolean
     */
    suspend fun setBoolean(
        key: String,
        value: Boolean,
    ) {
        val booleanPreferencesKey = booleanPreferencesKey(key)
        mContext.dataStore.edit {
            it[booleanPreferencesKey] = value
        }
    }

    /**
     * 取Boolean
     */
    suspend fun getBoolean(
        key: String,
        default: Boolean = false,
    ): Boolean {
        val booleanPreferencesKey = booleanPreferencesKey(key)
        val booleanValueFlow = mContext.dataStore.data.map {
            it[booleanPreferencesKey] ?: default
        }
        return booleanValueFlow.first()
    }

    suspend fun clear() {
        mContext.dataStore.edit {
            it.clear()
        }
    }
}