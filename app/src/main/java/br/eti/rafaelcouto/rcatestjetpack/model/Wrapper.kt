package br.eti.rafaelcouto.rcatestjetpack.model

import android.os.Parcelable

data class Wrapper<T: Parcelable>(var code: Int, var status: String, var data: T?)