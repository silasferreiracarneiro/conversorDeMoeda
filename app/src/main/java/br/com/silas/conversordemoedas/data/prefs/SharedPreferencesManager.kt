package br.com.silas.conversordemoedas.data.prefs

import android.content.Context
import android.content.SharedPreferences
import br.com.silas.conversordemoedas.utils.Constants
import br.com.silas.conversordemoedas.utils.Constants.IS_NETWORK

class SharedPreferencesManager(context: Context) {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    suspend fun salvaSeEhParaUsarDadosMoveis(isNetwork: Boolean): Boolean {
        return try {
            mPrefs.edit().putBoolean(IS_NETWORK, isNetwork).apply()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getSeEhParaUsarDadosMoveis() : Boolean =
        mPrefs.getBoolean(IS_NETWORK, false)
}