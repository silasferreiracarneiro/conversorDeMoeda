package br.com.silas.conversordemoedas.provider

import android.content.Context
import br.com.silas.conversordemoedas.data.db.AppDatabase

fun providerRoom(context: Context) = AppDatabase.invoke(context).apiDaoLocal()