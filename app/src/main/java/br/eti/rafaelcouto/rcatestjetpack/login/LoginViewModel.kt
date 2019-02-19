package br.eti.rafaelcouto.rcatestjetpack.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.eti.rafaelcouto.rcatestjetpack.model.User
import br.eti.rafaelcouto.rcatestjetpack.network.WebService

class LoginViewModel: ViewModel() {
    val login = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    val canEnable: Boolean
        get() = !(login.value.isNullOrEmpty() || password.value.isNullOrEmpty())

    private val webService by lazy {
        WebService()
    }

    fun login(): MutableLiveData<User>? {
        password.value?.let { passwd ->
            val data = MutableLiveData<User>()

            webService.login(
                passwd,
                success = { result ->
                    data.value = result
                }, failure = { strError ->
                    error.value = strError
                }
            )

            return data
        }

        return null
    }
}