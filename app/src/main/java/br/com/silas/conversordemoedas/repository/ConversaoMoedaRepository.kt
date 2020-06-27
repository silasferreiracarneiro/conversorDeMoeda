package br.com.silas.conversordemoedas.repository

import br.com.silas.conversordemoedas.api.Api
import br.com.silas.conversordemoedas.api.config.ResultApi
import br.com.silas.conversordemoedas.api.config.doResquest
import java.math.BigDecimal

class ConversaoMoedaRepository(private val api: Api) {

//    suspend fun converteMoeda(de: String, para: String, valor: BigDecimal) : ResultApi<ConverteMoedaResponse> =
//        doResquest {
//            api.converteMoeda(de = de, para = para, valor = valor).await()
//        }
}