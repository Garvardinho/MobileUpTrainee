package com.garvardinho.moblieuptrainee.view.details

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDetailsDTO
import com.garvardinho.moblieuptrainee.view.MobileUpView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface DetailsView : MobileUpView, MvpView {

    fun showDetails(details: CoinDetailsDTO)
}