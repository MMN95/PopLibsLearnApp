package ru.mmn.poplibslearnapp.presenter

import moxy.MvpPresenter
import ru.mmn.poplibslearnapp.model.CountersModel
import ru.mmn.poplibslearnapp.view.IMainView

class MainPresenter(val model: CountersModel) : MvpPresenter<IMainView>() {

    fun counterOneClick() {
        val nextValue = model.next(0)
        viewState.setButtonOneText(nextValue.toString())
    }

    fun counterTwoClick() {
        val nextValue = model.next(1)
        viewState.setButtonTwoText(nextValue.toString())
    }

    fun counterThreeClick() {
        val nextValue = model.next(2)
        viewState.setButtonThreeText(nextValue.toString())
    }

}
