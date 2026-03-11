package com.example.mobile_smart_pantry_project_iv

import kotlinx.serialization.Serializable

@Serializable
data class Product (
    val  id: Int,
    val nazwa: String,
    val ilosc: Double,
    val jednostka: String,
    val kategoria: String
)