package com.garvardinho.moblieuptrainee.view.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.garvardinho.moblieuptrainee.App
import com.garvardinho.moblieuptrainee.R
import com.garvardinho.moblieuptrainee.databinding.FragmentHomeBinding
import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.garvardinho.moblieuptrainee.presenter.home.HomeCoinsAdapter
import com.garvardinho.moblieuptrainee.presenter.home.HomeViewPresenter
import com.garvardinho.moblieuptrainee.presenter.home.MobileUpItemClickListener
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class HomeFragment : MvpAppCompatFragment(), HomeView {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val presenter by moxyPresenter {
        HomeViewPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val homeCoinsAdapter by lazy { HomeCoinsAdapter(presenter.homeCardViewPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chipUSD = binding.toolbar.usdChip
        val chipEUR = binding.toolbar.eurChip
        var lastTappedIsUSD = true

        chipUSD.setOnClickListener {
            presenter.loadCoins("usd")
            chipUSD.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_checked))
            chipUSD.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_checked_text))
            chipEUR.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_unchecked))
            chipEUR.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_unchecked_text))
            lastTappedIsUSD = true
        }

        chipEUR.setOnClickListener {
            presenter.loadCoins("eur")
            chipEUR.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_checked))
            chipEUR.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_checked_text))
            chipUSD.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_unchecked))
            chipUSD.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_unchecked_text))
            lastTappedIsUSD = false
        }

        binding.errorView.errorButton.setOnClickListener {
            val currency: String =
                if (lastTappedIsUSD)
                    "usd"
                else
                    "eur"

            presenter.loadCoins(currency)
        }

        binding.refresh.setOnRefreshListener {
            val currency: String =
                if (lastTappedIsUSD)
                    "usd"
                else
                    "eur"

            presenter.loadCoins(currency, false)
        }
    }

    override fun showCoins(coins: List<CoinDTO>) {
        binding.coinList.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.INVISIBLE
        binding.refresh.isRefreshing = false
        for (child in binding.errorView.root.children)
            child.visibility = View.INVISIBLE

        binding.coinList.layoutManager = LinearLayoutManager(requireContext())
        binding.coinList.adapter = homeCoinsAdapter

        homeCoinsAdapter.setOnCoinClickListener(object : MobileUpItemClickListener {
            override fun setListener(v: View, position: Int) {
                presenter.onCoinClicked(position)
            }
        })
    }

    override fun showLoading() {
        binding.coinList.visibility = View.INVISIBLE
        binding.loadingBar.visibility = View.VISIBLE
        for (child in binding.errorView.root.children)
            child.visibility = View.INVISIBLE
    }

    override fun showError() {
        binding.coinList.visibility = View.INVISIBLE
        binding.loadingBar.visibility = View.INVISIBLE
        for (child in binding.errorView.root.children)
            child.visibility = View.VISIBLE
    }

    override fun showRefreshError() {
        Snackbar.make(binding.root, resources.getString(R.string.refresh_error), Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.percent_below_zero))
            .show()
        binding.refresh.isRefreshing = false
    }
}
