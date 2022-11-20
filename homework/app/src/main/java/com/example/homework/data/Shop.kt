package com.example.homework.data

import androidx.annotation.DrawableRes

data class Shop (
    val id: Long,
    val name:String,
    @DrawableRes
    val image: Int?
)