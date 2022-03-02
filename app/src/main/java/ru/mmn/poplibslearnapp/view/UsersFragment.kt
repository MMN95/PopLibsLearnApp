package ru.mmn.poplibslearnapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mmn.poplibslearnapp.App
import ru.mmn.poplibslearnapp.R
import ru.mmn.poplibslearnapp.adapter.UsersRVAdapter
import ru.mmn.poplibslearnapp.databinding.FragmentUsersBinding
import ru.mmn.poplibslearnapp.model.ApiHolder
import ru.mmn.poplibslearnapp.model.GlideImageLoader
import ru.mmn.poplibslearnapp.model.RetrofitGithubUsersRepo
import ru.mmn.poplibslearnapp.presenter.UsersPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), IUsersView,
    BackButtonListener {

    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router,
            AndroidScreens()
        )
    }
    private var adapter: UsersRVAdapter? = null
    private var binding: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding?.rvUsers?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}

