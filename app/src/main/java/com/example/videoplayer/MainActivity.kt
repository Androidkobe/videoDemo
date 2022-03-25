package com.example.videoplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.recyclerview.extend.adapterX
import cc.xiaobaicz.recyclerview.extend.config
import com.example.videoplayer.alphavideo.AlphaActivity
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
    }

    private fun testData(data: MutableList<Any>) {
        data.add(Account("透明视频",AlphaActivity::class.java))
        bind.list.adapterX.notifyDataSetChanged()
    }


}