package com.garvardinho.moblieuptrainee.presenter

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO

interface RecyclerViewPresenter<V : MobileUpItemView> {

    fun bindView(view: V)
    fun getCount(): Int
    fun setCoins(coins: List<CoinDTO>, currency: String)
}
