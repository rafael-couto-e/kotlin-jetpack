package br.eti.rafaelcouto.rcatestjetpack.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Installment(
    @SerializedName("past_due") var pastDue: String,
    var carnet: String,
    var installment: String,
    var value: String,
    var detail: InstallmentDetail?
): Parcelable