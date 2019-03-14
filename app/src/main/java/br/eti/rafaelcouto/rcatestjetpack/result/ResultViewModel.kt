package br.eti.rafaelcouto.rcatestjetpack.result

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.eti.rafaelcouto.rcatestjetpack.model.Installment
import br.eti.rafaelcouto.rcatestjetpack.model.User

class ResultViewModel: ViewModel() {
    private val _user = MutableLiveData<User>()

    val username: LiveData<String> = Transformations.map(_user) { it.name }
    val available: LiveData<String> = Transformations.map(_user) { it.limits?.available }
    val total: LiveData<String> = Transformations.map(_user) { it.limits?.total }
    val totalDue: LiveData<String> = Transformations.map(_user) { it.limits?.totalDue }
    val installments: LiveData<List<Installment>> = Transformations.map(_user) { it.installments }

    fun fetchUser(extras: Bundle?) {
        (extras?.get("userData") as? User)?.let { userData ->
            this._user.value = userData
        }
    }
}