package com.garvardinho.moblieuptrainee.model.repository

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.garvardinho.moblieuptrainee.model.retrofit.IRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: IRemoteDataSource) : IRepository {

    override fun loadCoins(currency: String): Single<List<CoinDTO>> {
        return remoteDataSource.loadCoins(currency)
    }
}