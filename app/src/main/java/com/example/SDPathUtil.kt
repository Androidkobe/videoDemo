package com.example

import android.os.Environment

object SDPathUtil {

    //rootpath = /system
    val rootDirPath by lazy {
        Environment.getRootDirectory().absolutePath
    }

    //dataDirPath = /data
    val dataDirPath by lazy {
        Environment.getDataDirectory().absolutePath
    }

    //downloadCachePath = /data/cache
    val downloadCachePath by lazy {
        Environment.getDownloadCacheDirectory().absolutePath
    }

    //storeDirPath = /storage/emulated/0
    val storeDirPath by lazy {
        Environment.getExternalStorageDirectory().absolutePath
    }
}