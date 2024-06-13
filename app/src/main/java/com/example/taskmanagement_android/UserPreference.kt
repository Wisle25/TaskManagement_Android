package com.example.taskmanagement_android

import SecurePreferences
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    private val securePreferences = SecurePreferences(context)

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    suspend fun setLoggedIn(loggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = loggedIn
        }
    }

    val userToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN]
        }

    suspend fun setUserToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }

    suspend fun clearUserData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
        securePreferences.clearCredentials()
    }

    fun saveUserCredentials(username: String, password: String) {
        securePreferences.saveCredentials(username, password)
    }

    fun getUserCredentials(): Pair<String?, String?> {
        return Pair(securePreferences.getUsername(), securePreferences.getPassword())
    }
}
