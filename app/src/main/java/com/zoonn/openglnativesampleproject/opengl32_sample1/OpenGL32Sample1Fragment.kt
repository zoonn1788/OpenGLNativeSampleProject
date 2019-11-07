package com.zoonn.openglnativesampleproject.opengl32_sample1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zoonn.openglnativesampleproject.R

class OpenGL32Sample1Fragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_open_gl32_sample1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.opengles32_sample1_text).text = stringFromJNI()
    }

    external fun stringFromJNI(): String

    companion object {

        fun newInstance() = OpenGL32Sample1Fragment()

        init {
            System.loadLibrary("native-lib")
        }
    }
}