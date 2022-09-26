package com.garvardinho.moblieuptrainee

import android.app.Application
import com.garvardinho.moblieuptrainee.model.di.components.AppComponent
import com.garvardinho.moblieuptrainee.model.di.components.DaggerAppComponent
import com.garvardinho.moblieuptrainee.model.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}