package ru.mmn.poplibslearnapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mmn.poplibslearnapp.App
import ru.mmn.poplibslearnapp.R
import javax.inject.Inject

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    //При выполнении практического задания это должно отсюда уйти
    @Inject
    lateinit var router: Router

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: RepositoriesRVAdapter? = null

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(router, repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_repository, null)

    override fun init() {}

    override fun setId(text: String) {
        tv_id.text = text
    }

    override fun setTitle(text: String) {
        tv_title.text = text
    }

    override fun setForksCount(text: String) {
        tv_forksCount.text = text
    }

    override fun backPressed() = presenter.backPressed()
}

