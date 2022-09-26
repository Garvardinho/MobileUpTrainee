package com.garvardinho.moblieuptrainee.model.retrofit

import io.reactivex.rxjava3.core.Single

interface IRemoteDataSource {

    fun loadCoins(currency: String): Single<List<CoinDTO>>

    fun loadDetails(id: String): Single<CoinDetailsDTO>
}