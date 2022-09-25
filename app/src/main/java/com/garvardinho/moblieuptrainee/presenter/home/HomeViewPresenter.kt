package com.garvardinho.moblieuptrainee.presenter.home

import com.garvardinho.moblieuptrainee.model.repository.IRepository
import com.garvardinho.moblieuptrainee.view.home.HomeView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class HomeViewPresenter : MvpPresenter<HomeView>(), HomeViewDelegate {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repository: IRepository

    val homeCardViewPresenter = HomeCardViewPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadCoins("usd")
    }

    override fun loadCoins(currency: String) {
        viewState.showLoading()
        repository.loadCoins(currency)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { coins ->
                if (coins.isEmpty()) {
                    viewState.showError()
                } else {
                    homeCardViewPresenter.setCoins(coins, currency)
                    viewState.showCoins(coins)
                }
            }
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}