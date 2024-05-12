package com.sachin.personaldiary.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.sachin.personaldiary.R
import com.sachin.personaldiary.models.Diary
import java.text.SimpleDateFormat
import java.util.Locale

class MyAdapter(private val diariesList : ArrayList<Diary>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val imageView =LayoutInflater.from(parent.context).inflate(R.layout.fragment_view_diary,parent,false)
        return MyViewHolder(imageView)
    }

    override fun getItemCount(): Int {
        return diariesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = diariesList[position]
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())
        val bitmap = BitmapFactory.decodeByteArray(
            currentItem.imageData,0,currentItem.imageData.size
        )
        //holder.titleImg.setImageResource(currentItem.titleImg)
        //holder.tvHeading.text=currentItem.heading
        holder.dateTxt.text=dateFormat.format(currentItem.date)
        holder.titleTxt.text=currentItem.title
        holder.descrTxt.text=currentItem.description
        holder.imageV.setImageBitmap(bitmap)
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val dateTxt : TextView= itemView.findViewById(R.id.dateTxt)
        val titleTxt : TextView= itemView.findViewById(R.id.titleTxt)
        val descrTxt : TextView= itemView.findViewById(R.id.descrTxt)
        val imageV = itemView.findViewById<ImageView>(R.id.imageV)
        // val titleImg : ShapeableImageView= itemView.findViewById(R.id.)
        //val tvHeading : TextView= itemView.findViewById(R.id.tvHeading)
    }
}