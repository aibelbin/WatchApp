import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.healthbro.presentation.Models.Transaction


private val Context.dataStore by preferencesDataStore(name = "settings")

object DataStore {
    private val WALLET_AMOUNT_KEY = intPreferencesKey("WalletAmount")
    private val IS_FIRST_RUN = booleanPreferencesKey("isFirstRun")
    private val TRANSACTIONS_KEY = stringPreferencesKey("transactions")

    private val gson = Gson()

    suspend fun saveWalletAmount(context: Context, amount: Int) {
        context.dataStore.edit { preferences ->
            preferences[WALLET_AMOUNT_KEY] = amount
        }
    }

    suspend fun markFirstRun(context: Context){
        context.dataStore.edit { preferences ->
            preferences[IS_FIRST_RUN] = false
        }
    }

    fun isFirstRun(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[IS_FIRST_RUN] ?: true
            }
    }

    fun getWalletAmount(context: Context): Flow<Int> {
        return context.dataStore.data
            .map { preferences ->
                preferences[WALLET_AMOUNT_KEY] ?: 0
            }
    }

    suspend fun saveTransactions(context: Context, transactions: List<Transaction>) {
        val json = gson.toJson(transactions)
        context.dataStore.edit { preferences ->
            preferences[TRANSACTIONS_KEY] = json
        }
    }

    fun getTransactions(context: Context): Flow<List<Transaction>> {
        return context.dataStore.data
            .map { preferences ->
                val json = preferences[TRANSACTIONS_KEY] ?: ""
                if (json.isNotEmpty()) {
                    try {
                        val transactionArray = gson.fromJson(json, Array<Transaction>::class.java)
                        transactionArray.toList()
                    } catch (e: Exception) {
                        emptyList()
                    }
                } else {
                    emptyList()
                }
            }
    }
}
