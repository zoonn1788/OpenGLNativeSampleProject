package com.zoonn.openglnativesampleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoonn.openglnativesampleproject.opengl32_sample1.OpenGL32Sample1Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val assetsList: List<String> = listOf("native_kotlin.png", "open_gl10_1.png", "open_gl_es32_1.png")

    private val sampleList: List<OpenGLNativeSampleAdapter.OpenGLNativeSamples> = listOf(
        OpenGLNativeSampleAdapter.OpenGLNativeSamples("Android Native for Kotlin", "Hello from C++が表示されるサンプル", assetsList[0], HelloFromCppActivity::class.java),
        OpenGLNativeSampleAdapter.OpenGLNativeSamples("OpenGL ES3.2 Sample1", "NativeでOpenGLを使う", assetsList[2], OpenGL32Sample1Activity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //落ちる
        //register()
        //sample_text.text = CppFunction.stringFromJNI()

        findViewById<RecyclerView>(R.id.activity_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            if (sampleList.size >= 0) {
                adapter = OpenGLNativeSampleAdapter(sampleList)
            }
        }

    }

    fun start(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun register()

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }

        @JvmStatic
        fun hello(value: Int) = "Hello from Kotlin $value"
    }
}
