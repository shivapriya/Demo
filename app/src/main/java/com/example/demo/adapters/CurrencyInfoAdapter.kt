package com.example.demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.interfaces.OnItemClickListener
import com.example.demo.models.CurrencyInfo
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults

class CurrencyInfoAdapter (currencyInfoData: RealmResults<CurrencyInfo>?, mListener : OnItemClickListener) :
    RealmRecyclerViewAdapter<CurrencyInfo?, CurrencyInfoAdapter.CurrencyInfoHolder?>(currencyInfoData, true) {
    var listener = mListener
    var data = currencyInfoData

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyInfoHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.currencyinfo_row, parent, false)
        return CurrencyInfoHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: CurrencyInfoHolder,
        position: Int
    ) {
        var currencyInfo : CurrencyInfo? = data?.get(position)
        holder.title.setText(currencyInfo?.name)
        holder.symbol.setText(currencyInfo?.symbol)
        holder.name.setText(""+currencyInfo?.name?.get(0))
        holder.bind(data?.get(position), listener)
    }

    override fun getItemCount(): Int = data!!.size

    class CurrencyInfoHolder(view : View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var title: TextView
        var symbol: TextView
        var arrowimg: ImageButton

        init {
            name = view.findViewById(R.id.name)
            title = view.findViewById(R.id.title)
            symbol = view.findViewById(R.id.symbol)
            arrowimg = view.findViewById(R.id.arrowimg)
        }

        fun bind(item: CurrencyInfo?, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }
}