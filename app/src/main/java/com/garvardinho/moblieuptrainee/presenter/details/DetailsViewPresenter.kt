package com.garvardinho.moblieuptrainee.presenter.details

import com.garvardinho.moblieuptrainee.model.repository.IRepository
import com.garvardinho.moblieuptrainee.view.details.DetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailsViewPresenter : DetailsViewDelegate, MvpPresenter<DetailsView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repository: IRepository

    override fun loadDetails(id: String) {
        viewState.showLoading()
        repository.loadDetails(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { details -> viewState.showDetails(details) },
                { viewState.showError() }
            )
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}