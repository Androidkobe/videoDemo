package com.example.videoplayer.entity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.videoplayer.databinding.ItemAccountBinding

/**
 * 数据类
 */
data class Account(val name: String,val className:Class<*>) {
    /**
     * 自定义支持ViewBinding的ViewHolder
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bind by lazy {
            ItemAccountBinding.bind(view)
        }
    }


}