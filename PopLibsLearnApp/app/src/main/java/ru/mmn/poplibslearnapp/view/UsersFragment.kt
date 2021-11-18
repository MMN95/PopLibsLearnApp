package ru.mmn.poplibslearnapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mmn.poplibslearnapp.App
import ru.mmn.poplibslearnapp.adapter.UsersRVAdapter
import ru.mmn.poplibslearnapp.databinding.FragmentUsersBinding
import ru.mmn.poplibslearnapp.model.GlideImageLoader
import ru.mmn.poplibslearnapp.presenter.UsersPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), IUsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment().apply {
            App.instance.appComponent.inject(this)
        }
    }

    @Inject
    lateinit var database: Database

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread()).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(
            presenter.usersListPresenter,
            GlideImageLoader(RoomImageCache(database, App.instance.cacheDir), AndroidNetworkStatus(requireContext()))
        )
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}