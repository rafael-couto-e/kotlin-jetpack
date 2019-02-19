package br.eti.rafaelcouto.rcatestjetpack.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String,
    @SerializedName("total_overdue") var overdue: String,
    @SerializedName("total_due") var due: String,
    var installments: ArrayList<Installment>,
    var limits: Limits?
): Parcelable