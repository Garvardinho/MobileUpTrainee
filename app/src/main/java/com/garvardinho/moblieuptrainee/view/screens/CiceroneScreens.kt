package com.garvardinho.moblieuptrainee.view.screens

import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.garvardinho.moblieuptrainee.view.details.DetailsFragment
import com.garvardinho.moblieuptrainee.view.home.HomeFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object CiceroneScreens : MobileUpScreens {

    override fun homeScreen(): Screen = FragmentScreen { HomeFragment() }
    override fun detailsScreen(coin: CoinDTO): Screen =
        FragmentScreen { DetailsFragment.newInstance(coin) }
}