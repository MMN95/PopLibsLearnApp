package ru.mmn.poplibslearnapp.dagger

import dagger.Module
import dagger.Provides
import ru.mmn.poplibslearnapp.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()


    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)
}

