package ru.mmn.poplibslearnapp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.mmn.poplibslearnapp.view.IMainView
import ru.mmn.poplibslearnapp.view.IScreens
import javax.inject.Inject

class MainPresenter() : MvpPresenter<IMainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}

