package com.example.shualeduri

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentInfo(
    val itemPrice: String,
    val fullName: String,
    val cardNumber: Long,
    val expMonth: Int,
    val expYear: Int,
    val cvvCode: Int
): Parcelable