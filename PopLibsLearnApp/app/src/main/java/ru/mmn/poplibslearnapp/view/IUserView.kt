package ru.mmn.poplibslearnapp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface IUserView : MvpView {
    fun setLogin(text: String)
}