package br.com.silas.conversordemoedas.data.network.config

import br.com.silas.conversordemoedas.data.network.Api
import br.com.silas.conversordemoedas.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private fun getIntanceRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient().newBuilder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val api: Api = getIntanceRetrofit().create(
        Api::class.java)
}