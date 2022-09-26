package com.garvardinho.moblieuptrainee.model.retrofit;

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinDTO(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val image: String?,
    @field:SerializedName("current_price")
    val currentPrice: Double?,
    @field:SerializedName("price_change_percentage_24h")
    val priceChangePercentage: Double?,
) : Parcelable