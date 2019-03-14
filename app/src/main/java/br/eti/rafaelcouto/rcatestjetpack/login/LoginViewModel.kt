package br.eti.rafaelcouto.rcatestjetpack.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.eti.rafaelcouto.rcatestjetpack.model.User
import br.eti.rafaelcouto.rcatestjetpack.network.WebService

class LoginViewModel: ViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() { return _login }

    val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() { return _password }

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() { return _error }

    private val webService by lazy {
        WebService()
    }

    fun login(): LiveData<User>? {
        _password.value?.let { passwd ->
            val data = MutableLiveData<User>()

            webService.login(
                passwd,
                success = { result ->
                    data.value = result
                }, failure = { strError ->
                    _error.value = strError
                }
            )

            return data
        }

        return null
    }

    fun shouldEnable(): Boolean = !(login.value.isNullOrEmpty() || password.value.isNullOrEmpty())
}