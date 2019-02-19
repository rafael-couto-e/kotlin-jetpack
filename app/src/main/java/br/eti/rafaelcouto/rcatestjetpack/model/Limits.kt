package br.eti.rafaelcouto.rcatestjetpack.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Limits(
    @SerializedName("total_due") var totalDue: String,
    var total: String,
    var expent: String,
    var available: String
): Parcelable