package br.eti.rafaelcouto.rcatestjetpack.network

import br.eti.rafaelcouto.rcatestjetpack.model.User
import br.eti.rafaelcouto.rcatestjetpack.model.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWebService {
    @GET("extract.php")
    fun login(@Query("pwd") pwd: String): Call<Wrapper<User>>
}