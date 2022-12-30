package ai.pipi.dotapickone

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = "settings_pref"
)

class SettingsManager(private val context: Context) {

    companion object{
        val LAST_FETCH_KEY = intPreferencesKey(name = "last_fetch_time")
        val FIRST_TIME = booleanPreferencesKey(name = "first_time")
    }

    suspend fun saveLastFetchTime(time: Int){
        Log.d(javaClass.simpleName,"start savelastfetchtime")
        context.dataStore.edit { preferences ->
            preferences[LAST_FETCH_KEY] = time
        }
        Log.d(javaClass.simpleName,"end savelastfetchtime")
    }

    suspend fun getlastFetchTime(): Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_FETCH_KEY] ?: -1
        }


    suspend fun saveFirstTime(index: Boolean){
        Log.d(javaClass.simpleName,"start savefirsttime")
        context.dataStore.edit { preferences ->
            preferences[FIRST_TIME] = true
        }
        Log.d(javaClass.simpleName,"end savefirsttime")
    }

    suspend fun getFirstTime(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[FIRST_TIME] ?: false
        }
}