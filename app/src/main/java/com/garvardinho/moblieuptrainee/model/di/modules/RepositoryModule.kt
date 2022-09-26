package com.garvardinho.moblieuptrainee.model.di.modules

import com.garvardinho.moblieuptrainee.model.repository.IRepository
import com.garvardinho.moblieuptrainee.model.repository.Repository
import com.garvardinho.moblieuptrainee.model.retrofit.IRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun repository(remoteDataSource: IRemoteDataSource): IRepository = Repository(remoteDataSource)
}