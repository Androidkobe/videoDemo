package com.example.videoplayer.alphavideo

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.SDPathUtil
import com.example.videoplayer.databinding.ActivityAlphaBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.extractor.flv.FlvExtractor
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.video.VideoSize
import tv.danmaku.ijk.media.player.IMediaPlayer
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import java.io.File
import javax.sql.DataSource

class IjkPlayerActivity : AppCompatActivity() {

    val url1 =
        "http://n1cdn.miaopai.com/stream/oxX3t3Vm5XPHKUeTS-zbXA__.mp4?ssig=2780b01d36e187235c99ce38e21889d2&time_stamp=1648295405673"
    val url2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
    val url3 = SDPathUtil.storeDirPath + File.separator + "a.flv"
    val url4 = SDPathUtil.storeDirPath + File.separator + "b.flv"
    val url5 = SDPathUtil.storeDirPath + File.separator + "c.mp4"
    val bindView by lazy {
        ActivityAlphaBinding.inflate(layoutInflater)
    }

    val ijkplayer by lazy {
        IjkMediaPlayer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)
        setContentView(bindView.root)
        bindView.videoSurface.getSurface { surface ->
            ijkplayer.setSurface(surface)
        }
        ijkplayer.dataSource = url5
        ijkplayer.setOnPreparedListener {
            ijkplayer.start()
        }
        ijkplayer.setOnVideoSizeChangedListener(object :IMediaPlayer.OnVideoSizeChangedListener{
            override fun onVideoSizeChanged(p0: IMediaPlayer?, p1: Int, p2: Int, p3: Int, p4: Int) {
                bindView.videoSurface.layoutParams.height = (bindView.videoSurface.width
                        / (p1*1f/p2*1f)).toInt()
                bindView.videoSurface.requestLayout()
            }

        })
        ijkplayer.prepareAsync()
    }




    override fun onDestroy() {
        super.onDestroy()
        ijkplayer.stop()
        ijkplayer.release()
    }
}