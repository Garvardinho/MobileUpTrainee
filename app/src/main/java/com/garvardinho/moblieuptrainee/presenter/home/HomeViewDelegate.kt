package com.garvardinho.moblieuptrainee.presenter.home

import com.garvardinho.moblieuptrainee.presenter.ViewDelegate

interface HomeViewDelegate : ViewDelegate {

    fun loadCoins(currency: String)
}
