package br.com.silas.conversordemoedas.api

class ResultApi<SucessModel> {

    private constructor(value: SucessModel) {
        this.value = value
    }

    private constructor(value: Throwable) {
        this.error = value
    }

    var value: SucessModel? = null
        private set

    var error: Throwable? = null
        private set

    fun isSucess() = value != null

    companion object {
        fun <SucessModel> createSucess(value: SucessModel) = ResultApi(value)
        fun <SucessModel> createError(error: Throwable) = ResultApi<SucessModel>(error)
    }
}