package com.garvardinho.moblieuptrainee.view.home

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.garvardinho.moblieuptrainee.view.MobileUpView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface HomeView : MobileUpView, MvpView {

    fun showCoins(coins: List<CoinDTO>)
    fun showLoading()
}
