package ru.mmn.poplibslearnapp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.mmn.poplibslearnapp.model.GithubUser
import ru.mmn.poplibslearnapp.model.GithubUsersRepo
import ru.mmn.poplibslearnapp.view.IUserItemView
import ru.mmn.poplibslearnapp.view.IUserListPresenter
import ru.mmn.poplibslearnapp.view.IUsersView

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<IUsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя c помощью router.navigateTo
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}