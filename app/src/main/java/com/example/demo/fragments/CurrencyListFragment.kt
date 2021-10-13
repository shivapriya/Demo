package com.example.demo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapters.CurrencyInfoAdapter
import com.example.demo.models.CurrencyInfo
import io.realm.RealmResults
import android.widget.Toast
import com.example.demo.interfaces.OnItemClickListener

class CurrencyListFragment(data: RealmResults<CurrencyInfo>, mListener : OnItemClickListener): Fragment() {

    var currencyInfoRecyclerView : RecyclerView? = null
    lateinit var linearLayoutManager: LinearLayoutManager
    var results = data
    var listener = mListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView =  inflater.inflate(R.layout.fragment_currency_list, container, false)

        currencyInfoRecyclerView = rootView.findViewById(R.id.currencyInfoRecyclerView)
        linearLayoutManager = LinearLayoutManager(activity)
        currencyInfoRecyclerView?.layoutManager = linearLayoutManager
        currencyInfoRecyclerView?.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        setAdapter(results)
        return rootView
    }

    fun updateAdapter(results: RealmResults<CurrencyInfo>) {
//        adapter?.updateData(results)
        setAdapter(results)
    }

    fun setAdapter(results: RealmResults<CurrencyInfo>){
        currencyInfoRecyclerView?.setAdapter(CurrencyInfoAdapter(results, object :
            OnItemClickListener {
            override fun onItemClick(item: CurrencyInfo?) {
                listener.onItemClick(item)
            }
        }))
    }
}