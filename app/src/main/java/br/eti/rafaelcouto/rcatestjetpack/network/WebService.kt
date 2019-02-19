package br.eti.rafaelcouto.rcatestjetpack.network

import br.eti.rafaelcouto.rcatestjetpack.model.User
import br.eti.rafaelcouto.rcatestjetpack.model.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebService {
    private val endpoint by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.icoded.com.br/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IWebService::class.java)
    }

    fun login(pwd: String, success: (User) -> Unit, failure: (String) -> Unit) {
        endpoint.login(pwd).enqueue(object: Callback<Wrapper<User>> {
            override fun onResponse(call: Call<Wrapper<User>>, response: Response<Wrapper<User>>) {
                response.body()?.let { wrapper ->
                    wrapper.data?.let { user ->
                        success(user)

                        return
                    }

                    failure(wrapper.status)

                    return
                }

                failure(response.message())
            }

            override fun onFailure(call: Call<Wrapper<User>>, t: Throwable) {
                failure(t.localizedMessage)
            }
        })
    }
}