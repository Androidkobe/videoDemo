package com.example.videoplayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cc.xiaobaicz.recyclerview.extend.adapterX
import cc.xiaobaicz.recyclerview.extend.config
import com.example.videoplayer.alphavideo.ExoplayActivity
import com.example.videoplayer.alphavideo.IjkPlayerActivity
import com.example.videoplayer.databinding.ActivityMainBinding
import com.example.videoplayer.entity.*

class MainActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val data = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        bind.list.config(data) {
            addType<Account, Account.ViewHolder>(R.layout.item_account) { itemData, holderView, position, payloads ->
                //数据绑定
                with(holderView.bind) {
                    root.setOnClickListener {
                        startActivity(Intent(this@MainActivity,itemData.className))
                    }
                    name.text = itemData.name
                }
            }
        }
        testData(data)
        requestPermission()
    }

    private fun testData(data: MutableList<Any>) {
        data.add(Account("exoplayer",ExoplayActivity::class.java))
        data.add(Account("ijkplayer",IjkPlayerActivity::class.java))
        bind.list.adapterX.notifyDataSetChanged()
    }


    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.VIBRATE
                ),
                1
            )
        } else {
            Toast.makeText(this, "您已经申请了权限!", Toast.LENGTH_SHORT).show()
        }
    }

}