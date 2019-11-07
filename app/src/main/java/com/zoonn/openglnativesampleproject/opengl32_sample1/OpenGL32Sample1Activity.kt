package com.zoonn.openglnativesampleproject.opengl32_sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zoonn.openglnativesampleproject.R

class OpenGL32Sample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_gl32_sample1)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.opengles32_sample1_container, OpenGL32Sample1Fragment.newInstance())
                .commitNow()
        }
    }
}
