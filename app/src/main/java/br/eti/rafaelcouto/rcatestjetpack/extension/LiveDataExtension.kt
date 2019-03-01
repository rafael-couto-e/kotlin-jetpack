package br.eti.rafaelcouto.rcatestjetpack.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, S> MediatorLiveData<T>.withSource(
    source: LiveData<S>,
    onChanged: MediatorLiveData<T>.(S) -> Unit
): MediatorLiveData<T> {
    this.addSource(source) {
        onChanged(it)
    }

    return this
}