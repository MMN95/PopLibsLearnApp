package ru.mmn.poplibslearnapp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.mmn.poplibslearnapp.model.GithubUser
import ru.mmn.poplibslearnapp.view.IUserView

class UserPresenter(private val router: Router, private val user: GithubUser) :
    MvpPresenter<IUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}