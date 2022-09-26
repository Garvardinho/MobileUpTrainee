package com.garvardinho.moblieuptrainee.model.retrofit

data class CoinDetailsDTO(
    val description: Description?,
    val categories: List<String>?
)

data class Description(
    val en: String?
)
