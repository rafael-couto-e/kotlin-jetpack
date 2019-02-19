package br.eti.rafaelcouto.rcatestjetpack.result.recycler

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.eti.rafaelcouto.rcatestjetpack.model.Installment
import kotlinx.android.synthetic.main.row_installment.view.*

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val _installment = MutableLiveData<Installment>()

    var installment: Installment?
        get() = _installment.value
        set(value) { _installment.value = value }

    init {
        (itemView.context as? LifecycleOwner)?.let { owner ->
            _installment.observe(owner, Observer { item ->
                itemView.apply {
                    date.text = item.pastDue
                    carnet.text = item.installment
                    code.text = item.carnet
                    value.text = item.value
                }
            })
        }
    }
}