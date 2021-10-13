package com.example.demo

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.demo.fragments.CurrencyListFragment
import com.example.demo.interfaces.OnItemClickListener
import com.example.demo.interfaces.RealmUtilityListener
import com.example.demo.models.CurrencyInfo
import io.realm.RealmResults
import sg.example.demo.controllers.RealmController

class DemoActivity : AppCompatActivity(), RealmUtilityListener, OnItemClickListener {

    var loadDataBtn : Button? = null
    var sortDataBtn : Button? = null
    var fragmentContainerView : FragmentContainerView? = null
    var currencyListFragment : CurrencyListFragment? = null
    lateinit var realmController : RealmController
    val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        loadDataBtn = findViewById(R.id.loadDataBtn)
        sortDataBtn = findViewById(R.id.sortDataBtn)
        fragmentContainerView = findViewById(R.id.fragmentContainerView)

        loadDataBtn?.setOnClickListener{
            realmController.loadDBFromJson()
        }

        sortDataBtn?.setOnClickListener{
            realmController.sortDB()
        }

        realmController = RealmController(this, this)
    }

    override fun returnLoadedDB(results: RealmResults<CurrencyInfo>) {
        //pass to fragment
        currencyListFragment = CurrencyListFragment(results, this)
        addFragmentToActivity(currencyListFragment)
    }

    override fun loadSortedData(results: RealmResults<CurrencyInfo>){
        currencyListFragment?.updateAdapter(results)
    }

    private fun addFragmentToActivity(fragment: Fragment?){
        if (fragment == null) return
        val tr = fm.beginTransaction()
        tr.replace(R.id.fragmentContainerView, fragment)
        tr.commit()
    }

    override fun onItemClick(item: CurrencyInfo?) {
        Toast.makeText(this, "Item "+item?.name+" Clicked And Returned To Parent", Toast.LENGTH_LONG).show()
    }


}