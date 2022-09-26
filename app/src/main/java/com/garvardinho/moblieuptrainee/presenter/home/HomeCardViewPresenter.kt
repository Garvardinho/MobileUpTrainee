package com.garvardinho.moblieuptrainee.presenter.home

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.garvardinho.moblieuptrainee.presenter.MobileUpCardView
import com.garvardinho.moblieuptrainee.presenter.RecyclerViewPresenter

class HomeCardViewPresenter : RecyclerViewPresenter<MobileUpCardView> {

    private val coins = mutableListOf<CoinDTO>()
    lateinit var currency: String

    override fun bindView(view: MobileUpCardView) {
        val coin = coins[view.pos]
        view.setSymbol(coin.symbol.orEmpty())
        view.setName(coin.name.orEmpty())
        view.setImage(coin.image.orEmpty())
        view.setCurrentPrice(coin.currentPrice ?: 0.0, currency)
        view.setPriceChangePercentage(coin.priceChangePercentage ?: 0.0)
    }

    override fun getCount(): Int = coins.size

    override fun setCoins(coins: List<CoinDTO>, currency: String) {
        this.coins.clear()
        this.coins.addAll(coins)
        this.currency = currency
    }

    fun getCoinAt(position: Int): CoinDTO = coins[position]
}