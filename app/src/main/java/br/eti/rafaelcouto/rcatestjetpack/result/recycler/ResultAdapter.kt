package br.eti.rafaelcouto.rcatestjetpack.result.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.eti.rafaelcouto.rcatestjetpack.R
import br.eti.rafaelcouto.rcatestjetpack.model.Installment

class ResultAdapter(
    context: Context,
    private val items: List<Installment>
): RecyclerView.Adapter<ResultViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(inflater.inflate(viewType, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_installment
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.installment = items[position]
    }
}