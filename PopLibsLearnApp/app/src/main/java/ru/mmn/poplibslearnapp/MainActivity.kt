package ru.mmn.poplibslearnapp

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.mmn.poplibslearnapp.databinding.ActivityMainBinding
import ru.mmn.poplibslearnapp.model.CountersModel
import ru.mmn.poplibslearnapp.presenter.MainPresenter
import ru.mmn.poplibslearnapp.view.IMainView

class MainActivity : MvpAppCompatActivity(), IMainView {

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnCounter1?.setOnClickListener { presenter.counterOneClick() }
        binding?.btnCounter2?.setOnClickListener { presenter.counterTwoClick() }
        binding?.btnCounter3?.setOnClickListener { presenter.counterThreeClick() }
    }

    override fun setButtonOneText(text: String) {
        binding?.btnCounter1?.text = text
    }

    override fun setButtonTwoText(text: String) {
        binding?.btnCounter2?.text = text
    }

    override fun setButtonThreeText(text: String) {
        binding?.btnCounter3?.text = text
    }
}