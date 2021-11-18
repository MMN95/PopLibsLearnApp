package ru.mmn.poplibslearnapp.dagger

import dagger.Provides
import ru.mmn.poplibslearnapp.App

class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}