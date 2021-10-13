package sg.example.demo.controllers

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.demo.R
import com.example.demo.interfaces.RealmUtilityListener
import com.example.demo.models.CurrencyInfo
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import io.realm.internal.IOException
import io.realm.kotlin.where
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

class RealmController(var context: Context,
                      var mListener: RealmUtilityListener
) {

    var TAG : String = "RealmController"
    private lateinit var realm: Realm

    init{
        try {
            initRealm()
        } catch (ex:Exception) {
            Log.e(TAG, "Error occurred in RealmController init : "+ex.message.toString())
        }
    }

    fun initRealm() {
        realm = Realm.getDefaultInstance()
    }

    fun loadDBFromJson() {
        deleteAll()
    }

    fun deleteAll() {
        realm.executeTransactionAsync({ bgRealm ->
            var allInfo = bgRealm.where<CurrencyInfo>().findAll()
            allInfo?.deleteAllFromRealm()

        }, {
            val json : String = context.getResources().openRawResource(R.raw.currencyinfo)
                .bufferedReader().use { it.readText() }
            realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            try {
                realm.createAllFromJson(CurrencyInfo::class.java, json)
                realm.commitTransaction()
            } catch (e: IOException) {
                realm.cancelTransaction()
            }
            realm.close()
            getAllCurrencyInfo()
        }) {
            // Transaction failed and was automatically canceled.
            Toast.makeText(context, "deleteTrack Transaction was cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllCurrencyInfo() {
        var results : RealmResults<CurrencyInfo> = realm.where<CurrencyInfo>().findAll()
        mListener.returnLoadedDB(results)
    }

    fun sortDB(){
        var results : RealmResults<CurrencyInfo> = realm.where<CurrencyInfo>().findAll().sort("name", Sort.ASCENDING)
        mListener.loadSortedData(results)
    }
}