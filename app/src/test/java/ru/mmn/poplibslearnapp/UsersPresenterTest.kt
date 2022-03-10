package ru.mmn.poplibslearnapp

import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.atLeastOnce
import io.reactivex.rxjava3.core.Scheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ru.mmn.poplibslearnapp.model.IGithubUsersRepo
import ru.mmn.poplibslearnapp.presenter.UsersPresenter
import ru.mmn.poplibslearnapp.view.IScreens
import ru.mmn.poplibslearnapp.view.IUserItemView

class UsersPresenterTest {

    private lateinit var usersPresenter: UsersPresenter

    private lateinit var usersListPresenter: UsersPresenter.UsersListPresenter

    @Mock
    private lateinit var uiScheduler: Scheduler

    @Mock
    private lateinit var usersRepo: IGithubUsersRepo

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var screens: IScreens

    @Mock
    private lateinit var view: IUserItemView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        usersPresenter = UsersPresenter(uiScheduler, usersRepo, router, screens)
        usersListPresenter = UsersPresenter.UsersListPresenter()
    }

    @Test
    fun getCount_Test() {
        assertEquals(usersListPresenter.getCount(), usersListPresenter.users.size)
    }

    @Test
    fun getUsers_Test() {
        usersRepo.getUsers()
        verify(usersRepo, atLeastOnce()).getUsers()
    }

    @Test
    fun loadData_Test() {
        usersPresenter.loadData()
        verify(usersRepo, atLeastOnce()).getUsers().observeOn(uiScheduler).subscribe()
    }

}