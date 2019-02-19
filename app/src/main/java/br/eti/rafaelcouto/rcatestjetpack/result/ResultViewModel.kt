package br.eti.rafaelcouto.rcatestjetpack.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.eti.rafaelcouto.rcatestjetpack.model.User

class ResultViewModel: ViewModel() {
    val user = MutableLiveData<User>()
}