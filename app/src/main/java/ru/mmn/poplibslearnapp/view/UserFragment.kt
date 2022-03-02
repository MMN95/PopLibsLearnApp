package ru.mmn.poplibslearnapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.mmn.poplibslearnapp.App
import ru.mmn.poplibslearnapp.R
import ru.mmn.poplibslearnapp.databinding.FragmentUserBinding
import ru.mmn.poplibslearnapp.model.GithubUser
import ru.mmn.poplibslearnapp.presenter.UserPresenter

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), IUserView, BackButtonListener {

    companion object {
        private const val USER = "USER"
        fun newInstance(user: GithubUser): Fragment = UserFragment().apply {
            arguments = bundleOf().apply {
                putParcelable(USER, user)
            }
        }
    }

    private var binding: FragmentUserBinding? = null
    private val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        UserPresenter(App.instance.router, user)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun setLogin(text: String) {
        binding?.userLogin?.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()
}
