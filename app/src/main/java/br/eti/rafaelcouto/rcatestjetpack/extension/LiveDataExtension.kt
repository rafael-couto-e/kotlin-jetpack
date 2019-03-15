package br.eti.rafaelcouto.rcatestjetpack.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, onChanged: (T) -> Unit) {
    this.observe(owner, Observer { onChanged(it) })
}

fun <T, S> MediatorLiveData<T>.withSource(
    source: LiveData<S>,
    onChanged: MediatorLiveData<T>.(S) -> Unit
): MediatorLiveData<T> {
    this.addSource(source) {
        onChanged(it)
    }

    return this
}

fun <T, S> MediatorLiveData<T>.withSource(
    source: LiveData<S>
): MediatorLiveDataChaining<T, S> {
    return MediatorLiveDataChaining(this, null, source)
}

class MediatorLiveDataChaining<T, S> (
    private val mediator: MediatorLiveData<T>,
    private var owner: LifecycleOwner?,
    first: LiveData<S>
) {
    private var sources: MutableList<LiveData<S>> = mutableListOf()

    init {
        sources.add(first)
    }

    fun and(source: LiveData<S>): MediatorLiveDataChaining<T, S> {
        sources.add(source)

        return this
    }

    fun mediate(owner: LifecycleOwner, onChanged: (S) -> Unit): MediatorLiveDataChaining<T, S> {
        this.owner = owner

        for (source in sources) {
            source.observe(owner, Observer {
                onChanged(it)
            })
        }

        return this
    }

    fun andObserve(onChanged: (T) -> Unit) {
        this.owner?.let { owner ->
            mediator.observe(owner, Observer {
                onChanged(it)
            })
        }
    }
}