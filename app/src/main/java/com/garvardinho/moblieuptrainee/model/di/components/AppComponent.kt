package com.garvardinho.moblieuptrainee.model.di.components

import com.garvardinho.moblieuptrainee.model.di.modules.AppModule
import com.garvardinho.moblieuptrainee.model.di.modules.CiceroneModule
import com.garvardinho.moblieuptrainee.model.di.modules.RemoteDataModule
import com.garvardinho.moblieuptrainee.model.di.modules.RepositoryModule
import com.garvardinho.moblieuptrainee.presenter.details.DetailsViewPresenter
import com.garvardinho.moblieuptrainee.presenter.home.HomeViewPresenter
import com.garvardinho.moblieuptrainee.view.MainActivity
import com.garvardinho.moblieuptrainee.view.details.DetailsFragment
import com.garvardinho.moblieuptrainee.view.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(homeViewPresenter: HomeViewPresenter)
    fun inject(detailsViewPresenter: DetailsViewPresenter)
}