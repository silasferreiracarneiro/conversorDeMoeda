package br.com.silas.conversordemoedas.provider

import android.content.Context
import br.com.silas.conversordemoedas.data.prefs.SharedPreferencesManager

fun providerSharedPreferencesManager(context: Context) = SharedPreferencesManager(context)