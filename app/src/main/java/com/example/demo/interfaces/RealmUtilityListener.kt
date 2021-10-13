package com.example.demo.interfaces

import com.example.demo.models.CurrencyInfo
import io.realm.RealmResults

interface RealmUtilityListener {
    fun returnLoadedDB(results: RealmResults<CurrencyInfo>)
    fun loadSortedData(results: RealmResults<CurrencyInfo>)
}