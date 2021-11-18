package ru.mmn.poplibslearnapp

import android.app.Application
import ru.mmn.poplibslearnapp.dagger.AppComponent
import ru.mmn.poplibslearnapp.dagger.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}

