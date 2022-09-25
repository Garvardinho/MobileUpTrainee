package com.garvardinho.moblieuptrainee.model.repository

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import io.reactivex.rxjava3.core.Single

interface IRepository {

    fun loadCoins(currency: String): Single<List<CoinDTO>>
}