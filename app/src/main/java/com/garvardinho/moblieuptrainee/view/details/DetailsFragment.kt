package com.garvardinho.moblieuptrainee.view.details

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.garvardinho.moblieuptrainee.App
import com.garvardinho.moblieuptrainee.databinding.FragmentDetailsBinding
import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.garvardinho.moblieuptrainee.model.retrofit.CoinDetailsDTO
import com.garvardinho.moblieuptrainee.presenter.details.DetailsViewPresenter
import com.garvardinho.moblieuptrainee.view.FragmentInitializer
import com.garvardinho.moblieuptrainee.view.KEY_PARAM
import com.squareup.picasso.Picasso
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailsFragment : MvpAppCompatFragment(), DetailsView {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var _coin: CoinDTO? = null
    private val coin get() = _coin!!

    private val presenter by moxyPresenter {
        DetailsViewPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        arguments?.let {
            _coin = if (Build.VERSION.SDK_INT >= 33)
                it.getParcelable(KEY_PARAM, CoinDTO::class.java)
            else
                @Suppress("DEPRECATION") it.getParcelable(KEY_PARAM)

            val id = coin.id
            presenter.loadDetails(id.orEmpty())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.backButton.setOnClickListener {
            presenter.onBackPressed()
        }
    }

    override fun showDetails(details: CoinDetailsDTO) {
        binding.toolbar.title.text = coin.name
        Picasso
            .get()
            .load(coin.image)
            .into(binding.coinImage)

        binding.categories.text = details.categories?.joinToString()
        binding.categories.movementMethod = ScrollingMovementMethod.getInstance()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.description.movementMethod = LinkMovementMethod.getInstance()
            binding.description.text = Html.fromHtml(details.description?.en, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun showLoading() {

    }

    override fun showError() {

    }

    companion object : FragmentInitializer<CoinDTO>
}