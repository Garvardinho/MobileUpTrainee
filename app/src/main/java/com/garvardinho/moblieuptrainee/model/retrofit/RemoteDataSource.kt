package com.garvardinho.moblieuptrainee.model.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val coinAPI: CoinAPI) : IRemoteDataSource {

    override fun loadCoins(currency: String): Single<List<CoinDTO>> {
        return coinAPI.loadCoins(currency).subscribeOn(Schedulers.io())
    }
}