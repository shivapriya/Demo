package com.example.demo.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CurrencyInfo : RealmObject() {
    @PrimaryKey var id: String? = null
    var name: String? = null
    var symbol: String? = null
}