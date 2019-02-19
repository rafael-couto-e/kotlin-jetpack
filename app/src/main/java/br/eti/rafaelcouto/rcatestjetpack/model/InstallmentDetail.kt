package br.eti.rafaelcouto.rcatestjetpack.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InstallmentDetail(
    @SerializedName("overdue_days") var overdueDays: String,
    @SerializedName("overdue_date") var overdueDate: String,
    @SerializedName("original_value") var originalValue: String,
    @SerializedName("value_diff") var valueDiff: String,
    @SerializedName("total_value") var totalValue: String,
    var store: String
): Parcelable