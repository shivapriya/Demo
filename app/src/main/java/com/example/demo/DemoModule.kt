package com.example.demo

import com.example.demo.models.CurrencyInfo
import io.realm.annotations.RealmModule

// Create the module
@RealmModule(classes = [CurrencyInfo::class])
class DemoModule