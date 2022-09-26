package com.garvardinho.moblieuptrainee.model.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CoinAPI {

    @GET("markets")
    fun loadCoins(
        @Query("vs_currency") currency: String
    ): Single<List<CoinDTO>>

    @GET
    fun loadDetails(
        @Url id: String
    ): Single<CoinDetailsDTO>
}