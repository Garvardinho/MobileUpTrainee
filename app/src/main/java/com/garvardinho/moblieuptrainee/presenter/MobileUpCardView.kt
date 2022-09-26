package com.garvardinho.moblieuptrainee.presenter

interface MobileUpCardView : MobileUpItemView {

    fun setSymbol(symbol: String)
    fun setName(name: String)
    fun setImage(imageUrl: String)
    fun setCurrentPrice(currentPrice: Double, currency: String)
    fun setPriceChangePercentage(priceChangePercentage: Double)
}
