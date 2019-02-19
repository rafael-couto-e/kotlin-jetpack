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
        viewModel.user.observe(this, Observer { user ->
            name.text = user.name
            available.text = user.limits?.available
            limit.text = user.limits?.total
            used.text = user.limits?.totalDue

            setupRecyclerView()
        })
    }

    private fun fetch() {
        (intent.extras?.get("userData") as? User)?.let { userData ->
            this.viewModel.user.value = userData
        }
    }

    private fun setupRecyclerView() {
        list.apply {
            adapter = ResultAdapter(this@ResultActivity, viewModel.user.value?.installments ?: emptyList())
            layoutManager = LinearLayoutManager(this@ResultActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()

            adapter?.notifyDataSetChanged()
        }
    }

    private fun observeAndFetch() {
        observe()
        fetch()
    }
}
