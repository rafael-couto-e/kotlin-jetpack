package br.eti.rafaelcouto.rcatestjetpack.extension

import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData

fun <T: EditText> T.bind(callback: (String) -> Unit) {
    this.addTextChangedListener {
        callback(it.toString())
    }
}

fun <T: EditText> T.bindTo(dest: MutableLiveData<String>) {
    this.bind { dest.value = it }
}

fun <T: CompoundButton> T.bind(callback: (Boolean) -> Unit) {
    this.setOnCheckedChangeListener { _, isChecked ->
        callback(isChecked)
    }
}

fun <T: CompoundButton> T.bindTo(dest: MutableLiveData<Boolean>) {
    this.bind { dest.value = it }
}

fun <T: RadioGroup> T.bind(callback: (Int) -> Unit) {
    this.setOnCheckedChangeListener { _, checkedId ->
        callback(checkedId)
    }
}

fun <T: RadioGroup> T.bindTo(dest: MutableLiveData<Int>) {
    this.bind { dest.value = it }
}