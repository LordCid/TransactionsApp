package com.albertcid.transactionsapp.presentation.transaction.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertcid.transactionsapp.R
import com.albertcid.transactionsapp.domain.model.Transaction
import com.albertcid.transactionsapp.presentation.model.TransactionUIModel
import com.github.debop.kodatimes.toIsoFormatDateString
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.properties.Delegates

class TransactionsAdapter: RecyclerView.Adapter<ListItemViewHolder>() {

    var dataList: List<TransactionUIModel> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_list, parent, false)
        return ListItemViewHolder(
            view
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

}

class ListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(transaction: TransactionUIModel) {
        with(itemView) {
            total_amount_tv.text = transaction.amount.toString()
            date_tv.text = transaction.date.toIsoFormatDateString()
            description_tv.text = transaction.description
        }
    }

}