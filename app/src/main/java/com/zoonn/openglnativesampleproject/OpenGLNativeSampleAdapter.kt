package com.zoonn.openglnativesampleproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OpenGLNativeSampleAdapter(private val activitiesList: List<OpenGLNativeSamples>): RecyclerView.Adapter<OpenGLNativeSampleAdapter.ViewHolder>() {
    data class OpenGLNativeSamples(var title: String, var description: String, var assetPath: String = "", var clazz: Class<*> = MainActivity::class.java)
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.activities_item_image)
        val title: TextView = view.findViewById(R.id.activities_item_title)
        val description: TextView = view.findViewById(R.id.activities_item_description)
        var activity: Class<*>? = null

        init {
            view.setOnClickListener { v ->
                val context = v.context as MainActivity
                activity?.let {
                    context.start(it)
                }
            }
        }
    }

    private lateinit var imageList: MutableList<Bitmap>

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        activitiesList?.let {
            holder.apply {
                imageList?.let { bitmaps ->
                    image.setImageBitmap(bitmaps[position])
                }
                title.text = it[position].title
                description.text = it[position].description
                activity = it[position].clazz
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activities_item, parent, false)
        imageList = mutableListOf<Bitmap>().apply{
            activitiesList.forEach {
                view.resources.assets.open(it.assetPath).use { inputStream ->
                    add(BitmapFactory.decodeStream(inputStream))
                }
            }
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = activitiesList.size
}