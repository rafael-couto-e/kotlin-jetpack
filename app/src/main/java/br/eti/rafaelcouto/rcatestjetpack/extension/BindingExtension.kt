package br.eti.rafaelcouto.rcatestjetpack.extension

import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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