package com.zoonn.openglnativesampleproject.opengl32_sample1.opengl

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class OpenGL32Sample1Renderer: GLSurfaceView.Renderer {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        NDKonSurfaceCreated()
    }

    override fun onDrawFrame(gl: GL10?) {
        NDKonDrawFrame()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        NDKonSurfaceChanged(width, height)
    }

    external fun NDKonSurfaceCreated()
    external fun NDKonDrawFrame()
    external fun NDKonSurfaceChanged(width: Int, height: Int)
}