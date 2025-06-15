import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

object DataStore {
    private val WALLET_AMOUNT_KEY = intPreferencesKey("WalletAmount")

    suspend fun saveWalletAmount(context: Context, amount: Int) {
        context.dataStore.edit { preferences ->
            preferences[WALLET_AMOUNT_KEY] = amount
        }
    }

    fun getWalletAmount(context: Context): Flow<Int> {
        return context.dataStore.data
            .map { preferences ->
                preferences[WALLET_AMOUNT_KEY] ?: 0
            }
    }
}
