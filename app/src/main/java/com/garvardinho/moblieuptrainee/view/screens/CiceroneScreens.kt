package com.garvardinho.moblieuptrainee.view.screens

import com.garvardinho.moblieuptrainee.view.home.HomeFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object CiceroneScreens : MobileUpScreens {

    override fun homeScreen(): Screen = FragmentScreen { HomeFragment() }
}