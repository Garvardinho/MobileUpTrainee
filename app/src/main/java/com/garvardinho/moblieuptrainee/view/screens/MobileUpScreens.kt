package com.garvardinho.moblieuptrainee.view.screens

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.github.terrakok.cicerone.Screen

interface MobileUpScreens {

    fun homeScreen(): Screen

    fun detailsScreen(coin: CoinDTO): Screen
}
