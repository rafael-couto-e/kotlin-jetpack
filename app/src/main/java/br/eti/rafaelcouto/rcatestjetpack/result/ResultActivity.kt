package br.eti.rafaelcouto.rcatestjetpack.result

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.eti.rafaelcouto.rcatestjetpack.R
import br.eti.rafaelcouto.rcatestjetpack.extension.bindTo
import br.eti.rafaelcouto.rcatestjetpack.model.User
import br.eti.rafaelcouto.rcatestjetpack.result.recycler.ResultAdapter
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ResultViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        observeAndFetch()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun observe() {
        available.bindTo(viewModel.available)

        viewModel.apply {
            name.bindTo(username)
            limit.bindTo(total)
            used.bindTo(totalDue)
            list.bindTo(viewModel.installments) { ResultAdapter(this@ResultActivity, it) }
        }
    }

    private fun fetch() {
        this.viewModel.fetchUser(intent.extras)
    }

    private fun observeAndFetch() {
        observe()
        fetch()
    }
}
