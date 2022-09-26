package com.garvardinho.moblieuptrainee.presenter.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.garvardinho.moblieuptrainee.R
import com.garvardinho.moblieuptrainee.databinding.CoinCardViewBinding
import com.garvardinho.moblieuptrainee.presenter.MobileUpCardView
import com.garvardinho.moblieuptrainee.presenter.RecyclerViewPresenter
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.round

class HomeCoinsAdapter @Inject constructor
    (private val presenter: RecyclerViewPresenter<MobileUpCardView>) :
    RecyclerView.Adapter<HomeCoinsAdapter.ViewHolder>(), CoinsAdapter {

    private var onCoinClickListener: MobileUpItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CoinCardViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun setOnCoinClickListener(onCoinClickListener: MobileUpItemClickListener) {
        this.onCoinClickListener = onCoinClickListener
    }

    inner class ViewHolder(private val cardView: CoinCardViewBinding) :
        RecyclerView.ViewHolder(cardView.root),
        MobileUpCardView {

        init {
            cardView.root.setOnClickListener {
                onCoinClickListener?.setListener(it, adapterPosition)
            }
        }
        override var pos: Int = -1

        override fun setSymbol(symbol: String) {
            cardView.symbol.text = symbol.uppercase()
        }

        override fun setName(name: String) {
            cardView.name.text = name
        }

        override fun setImage(imageUrl: String) {
            Picasso
                .get()
                .load(imageUrl)
                .into(cardView.image)
        }

        @SuppressLint("SetTextI18n")
        override fun setCurrentPrice(currentPrice: Double, currency: String) {
            val formatter = NumberFormat.getCurrencyInstance()
            formatter.maximumFractionDigits = 2

            if (currency == "eur")
                formatter.currency = Currency.getInstance("EUR")

            cardView.price.text = formatter.format(currentPrice)
        }

        @SuppressLint("SetTextI18n")
        override fun setPriceChangePercentage(priceChangePercentage: Double) {
            val percent = round(priceChangePercentage * 100) / 100

            if (percent < 0) {
                cardView.percentage.text = "$percent%"
                cardView.percentage.setTextColor(
                    ContextCompat.getColor(cardView.root.context, R.color.percent_below_zero)
                )
            } else if (percent > 0) {
                cardView.percentage.text = "+$percent%"
                cardView.percentage.setTextColor(
                    ContextCompat.getColor(cardView.root.context, R.color.percent_above_zero)
                )
            } else {
                cardView.percentage.text = "$percent%"
            }
        }
    }
}
