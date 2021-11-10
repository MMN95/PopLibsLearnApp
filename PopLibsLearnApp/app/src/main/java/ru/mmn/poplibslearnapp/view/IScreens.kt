package ru.mmn.poplibslearnapp.view

import com.github.terrakok.cicerone.Screen
import ru.mmn.poplibslearnapp.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}