package br.eti.rafaelcouto.rcatestjetpack.extension

import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private fun <T: EditText> T.bind(callback: (String) -> Unit) {
    this.addTextChangedListener {
        callback(it.toString())
    }
}

fun <T: EditText> T.bindTo(dest: LiveData<String>) {
    if (dest as? MutableLiveData<String> == null) return

    this.bind { dest.value = it }
}

private fun <T: CompoundButton> T.bind(callback: (Boolean) -> Unit) {
    this.setOnCheckedChangeListener { _, isChecked ->
        callback(isChecked)
    }
}

fun <T: CompoundButton> T.bindTo(dest: LiveData<Boolean>) {
    if (dest as? MutableLiveData<Boolean> == null) return

    this.bind { dest.value = it }
}

private fun <T: RadioGroup> T.bind(callback: (Int) -> Unit) {
    this.setOnCheckedChangeListener { _, checkedId ->
        callback(checkedId)
    }
}

fun <T: RadioGroup> T.bindTo(dest: LiveData<Int>) {
    if (dest as? MutableLiveData<Int> == null) return

    this.bind { dest.value = it }
}

fun <T: TextView> T.bindTo(dest: LiveData<String>) {
    (this.context as? LifecycleOwner)?.let {
        dest.observe(it, Observer { value ->
            this.text = value
        })
    }
}

fun <RV: RecyclerView, I, VH: RecyclerView.ViewHolder, AD: RecyclerView.Adapter<VH>> RV.bindTo(
    dest: LiveData<List<I>>, adapter: (values: List<I>) -> AD
) {
    val ctx = this.context

    (ctx as? LifecycleOwner)?.let {
        dest.observe(it, Observer { values ->
            this.adapter = adapter(values)
            layoutManager = LinearLayoutManager(ctx, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()

            this.adapter?.notifyDataSetChanged()
        })
    }
}