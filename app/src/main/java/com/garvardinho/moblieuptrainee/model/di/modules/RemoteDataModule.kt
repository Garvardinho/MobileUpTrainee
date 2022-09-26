package com.garvardinho.moblieuptrainee.model.di.modules

import com.garvardinho.moblieuptrainee.model.retrofit.CoinAPI
import com.garvardinho.moblieuptrainee.model.retrofit.IRemoteDataSource
import com.garvardinho.moblieuptrainee.model.retrofit.RemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class RemoteDataModule {

    @Singleton
    @Binds
    abstract fun remoteDataSource(impl: RemoteDataSource): IRemoteDataSource

    companion object {
        @Named("baseUrl")
        @Provides
        fun baseUrl(): String = "https://api.coingecko.com/api/v3/coins/"

        @Singleton
        @Provides
        fun gson(): Gson = GsonBuilder().setLenient().create()

        @Singleton
        @Provides
        fun movieAPI(
            @Named("baseUrl") baseUrl: String,
            gson: Gson,
        ): CoinAPI = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CoinAPI::class.java)
    }
}