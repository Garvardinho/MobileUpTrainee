package com.garvardinho.moblieuptrainee.model.di.modules

import com.garvardinho.moblieuptrainee.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun app(): App {
        return app
    }
}