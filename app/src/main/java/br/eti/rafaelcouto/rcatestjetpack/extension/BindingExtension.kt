package br.eti.rafaelcouto.rcatestjetpack.extension

import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener

fun <T: EditText> T.bind(callback: (String) -> Unit) {
    this.addTextChangedListener {
        callback(it.toString())
    }
}

fun <T: CompoundButton> T.bind(callback: (Boolean) -> Unit) {
    this.setOnCheckedChangeListener { _, isChecked ->
        callback(isChecked)
    }
}

fun <T: RadioGroup> T.bind(callback: (Int) -> Unit) {
    this.setOnCheckedChangeListener { _, checkedId ->
        callback(checkedId)
    }
}