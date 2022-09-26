package com.garvardinho.moblieuptrainee.presenter.home

interface HomeViewDelegate {

    fun loadCoins(currency: String, isLoadingViewNeeded: Boolean = true)
}
