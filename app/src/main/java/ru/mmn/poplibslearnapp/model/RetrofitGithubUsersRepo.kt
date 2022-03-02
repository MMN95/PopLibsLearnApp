package ru.mmn.poplibslearnapp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())
}