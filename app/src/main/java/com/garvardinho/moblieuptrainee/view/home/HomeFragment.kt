package com.garvardinho.moblieuptrainee.view.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.garvardinho.moblieuptrainee.App
import com.garvardinho.moblieuptrainee.R
import com.garvardinho.moblieuptrainee.databinding.FragmentHomeBinding
import com.garvardinho.moblieuptrainee.model.retrofit.CoinDTO
import com.garvardinho.moblieuptrainee.presenter.home.HomeCoinsAdapter
import com.garvardinho.moblieuptrainee.presenter.home.HomeViewPresenter
import com.garvardinho.moblieuptrainee.presenter.home.MobileUpItemClickListener
import com.garvardinho.moblieuptrainee.view.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HomeFragment : MvpAppCompatFragment(), HomeView, BackButtonListener {

    @Inject
    lateinit var router: Router

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
        App.instance.appComponent.inject(this)
        return binding.root
    }

    override fun showCoins(coins: List<CoinDTO>) {
        binding.coinList.layoutManager = LinearLayoutManager(requireContext())
        binding.coinList.adapter = homeCoinsAdapter

        homeCoinsAdapter.setOnCoinClickListener(object : MobileUpItemClickListener {
            override fun setListener(v: View, position: Int) {
                Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chipUSD = binding.toolbar.usdChip
        val chipEUR = binding.toolbar.eurChip

        chipUSD.setOnClickListener {
            presenter.loadCoins("usd")
            chipUSD.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_checked))
            chipUSD.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_checked_text))
            chipEUR.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_unchecked))
            chipEUR.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_unchecked_text))
        }

        chipEUR.setOnClickListener {
            presenter.loadCoins("eur")
            chipEUR.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_checked))
            chipEUR.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_checked_text))
            chipUSD.chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_unchecked))
            chipUSD.setTextColor(ContextCompat.getColor(requireContext(), R.color.chip_unchecked_text))
        }
    }

    override fun showLoading(loading: Boolean) {
    }

    override fun showError() {
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }
}
