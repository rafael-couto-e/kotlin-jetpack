package br.eti.rafaelcouto.rcatestjetpack.login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.eti.rafaelcouto.rcatestjetpack.R
import br.eti.rafaelcouto.rcatestjetpack.result.ResultActivity
import br.eti.rafaelcouto.rcatestjetpack.extension.bindTo
import br.eti.rafaelcouto.rcatestjetpack.extension.withSource
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val loader by lazy {
        ProgressDialog(this)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindAndObserve()
    }

    private fun bind() {
        editText.bindTo(viewModel.login)
        editText2.bindTo(viewModel.password)
    }

    private fun observe() {
        viewModel.apply {
            val mediator = MediatorLiveData<Boolean>()
            val ctx = this@LoginActivity

            mediator.withSource(login)
                .and(password)
                .mediate(ctx) {
                    mediator.value = shouldEnable()
                }.andObserve { enabled ->
                    button.apply {
                        isEnabled = enabled
                        alpha = if (enabled) 1F else 0.5F
                    }
                }

            /*MediatorLiveData<Boolean>()
                .withSource(login) {
                    this.value = shouldEnable()
                }.withSource(password) {
                    this.value = shouldEnable()
                }.observe(
                    ctx,
                    Observer { enabled ->
                        button.apply {
                            isEnabled = enabled
                            alpha = if (enabled) 1F else 0.5F
                        }
                    }
                )*/

            error.observe(ctx, Observer { error ->
                loader.hide()

                Toast.makeText(ctx, error, Toast.LENGTH_SHORT).show()
            })

            button.setOnClickListener {
                loader.show()

                login()?.observe(ctx, Observer { data ->
                    loader.hide()

                    Intent(ctx, ResultActivity::class.java).apply {
                        putExtra("userData", data)
                        startActivity(this)
                    }
                })
            }
        }
    }

    private fun bindAndObserve() {
        this.bind()
        this.observe()
    }
}