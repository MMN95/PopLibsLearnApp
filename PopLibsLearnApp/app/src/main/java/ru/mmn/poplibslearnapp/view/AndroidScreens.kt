package ru.mmn.poplibslearnapp.view

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.mmn.poplibslearnapp.model.GithubUser

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser) = FragmentScreen {UserFragment.newInstance(user)}
}