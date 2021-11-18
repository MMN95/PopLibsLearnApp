package ru.mmn.poplibslearnapp.dagger

import dagger.Module
import dagger.Provides
import ru.mmn.poplibslearnapp.model.IDataSource
import ru.mmn.poplibslearnapp.model.IGithubUsersRepo
import ru.mmn.poplibslearnapp.model.RetrofitGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

}

