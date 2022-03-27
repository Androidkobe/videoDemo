package com.example.videoplayer.alphavideo

import android.content.Context
import android.graphics.Paint
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.util.Log
import android.view.Surface
import android.view.TextureView

class VideoSurface @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextureView(context, attrs), TextureView.SurfaceTextureListener {

    var mSurface: Surface? = null

    private lateinit var alive : ((pa:Surface) -> Unit)

    init {
        surfaceTextureListener = this
        isOpaque = false
    }

    fun getSurface(action:((pa:Surface) -> Unit)){
        Log.e("sundu","开始索要 surface")
        alive = action
        mSurface?.let{
            alive.invoke(mSurface!!)
        }
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        Log.e("sundu","onSurfaceTextureAvailable 创建 surface width $width height $height")
        mSurface = Surface(surface)
        alive.invoke(mSurface!!)
//        var paint = Paint()
//        paint.alpha = 100
//        setLayerType(LAYER_TYPE_HARDWARE,paint)
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        Log.e("sundu","onSurfaceTextureSizeChanged change surface width $width height $height")

        alive.invoke(mSurface!!)
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        Log.e("sundu","onSurfaceTextureDestroyed")
        mSurface = null
        return true
    }


    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
//        Log.e("sundu","onSurfaceTextureUpdated")
    }
}