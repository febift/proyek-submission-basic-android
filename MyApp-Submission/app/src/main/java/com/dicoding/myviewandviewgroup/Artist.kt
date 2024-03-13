package com.dicoding.myviewandviewgroup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val name: String,
    val description: String,
    val photo: Int,
) : Parcelable