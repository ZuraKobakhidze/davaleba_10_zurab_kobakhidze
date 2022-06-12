package com.example.davaleba_10_zurab_kobakhidze

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.davaleba_8.Item
import com.example.davaleba_8.MyAdapter

class MainActivity : AppCompatActivity() {

    val br: BroadcastReceiver = MyBroadCastReciever()

    lateinit var recyclerView: RecyclerView

    lateinit var itemList: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }

        sendBroadcast(Intent("NOTIFY"), Manifest.permission.SEND_SMS)

        registerReceiver(br, filter, Manifest.permission.SEND_SMS, Handler(Handler.Callback {
            itemList.add(Item("EVENT OCCURED"))
        }))

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = MyAdapter(itemList)

    }

}