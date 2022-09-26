package com.garvardinho.moblieuptrainee.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MobileUpView : MvpView {

    fun showError()
    fun showLoading()
}
