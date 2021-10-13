package com.example.demo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .name("currencyinfo.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .modules(DemoModule())
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

}