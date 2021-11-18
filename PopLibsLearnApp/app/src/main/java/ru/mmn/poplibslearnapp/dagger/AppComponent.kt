package ru.mmn.poplibslearnapp.dagger

import dagger.Component
import ru.mmn.poplibslearnapp.presenter.MainPresenter
import ru.mmn.poplibslearnapp.presenter.UsersPresenter
import ru.mmn.poplibslearnapp.view.MainActivity
import ru.mmn.poplibslearnapp.view.RepositoryFragment
import ru.mmn.poplibslearnapp.view.UserFragment
import ru.mmn.poplibslearnapp.view.UsersFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)

    //При выполнении практического задания это должно отсюда уйти
    fun inject(userFragment: UserFragment)
    fun inject(repositoryFragment: RepositoryFragment)
    fun inject(usersFragment: UsersFragment)
}

