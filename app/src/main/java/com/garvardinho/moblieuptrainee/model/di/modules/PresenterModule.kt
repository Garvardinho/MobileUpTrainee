package com.garvardinho.moblieuptrainee.model.di.modules

import com.garvardinho.moblieuptrainee.presenter.MobileUpCardView
import com.garvardinho.moblieuptrainee.presenter.RecyclerViewPresenter
import com.garvardinho.moblieuptrainee.presenter.home.HomeCardViewPresenter
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PresenterModule {

    @Singleton
    @Binds
    abstract fun getPresenter(presenter: HomeCardViewPresenter): RecyclerViewPresenter<MobileUpCardView>
}