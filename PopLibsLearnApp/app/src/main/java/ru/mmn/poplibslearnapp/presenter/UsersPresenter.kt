package ru.mmn.poplibslearnapp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.mmn.poplibslearnapp.model.GithubUser
import ru.mmn.poplibslearnapp.model.IGithubUsersRepo
import ru.mmn.poplibslearnapp.view.IScreens
import ru.mmn.poplibslearnapp.view.IUserItemView
import ru.mmn.poplibslearnapp.view.IUserListPresenter
import ru.mmn.poplibslearnapp.view.IUsersView
import javax.inject.Inject

class UsersPresenter(val uiScheduler: Scheduler) : MvpPresenter<IUsersView>() {

    @Inject lateinit var usersRepo: IGithubUsersRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}

