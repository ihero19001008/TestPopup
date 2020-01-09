package com.example.testpopup.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testpopup.R
import com.example.testpopup.model.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter (private var dataList: List<Photo>, private val context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val imgView: ImageView =itemLayoutView.findViewById(R.id.imgView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val dataModel =dataList[position]
        Picasso.get().load(dataModel.url).into(holder.imgView)
        Toast.makeText(context,dataModel.url,Toast.LENGTH_LONG).show()
    }
}