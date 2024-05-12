package com.sachin.personaldiary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sachin.personaldiary.R
import com.sachin.personaldiary.models.Diary
import java.text.SimpleDateFormat
import java.util.Locale

class DiaryRecyclerViewAdapter(
    private val deleteUpdateCallback: (type: String, position: Int, diary: Diary) -> Unit
) :
    RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder>() {

    private val diaryList = arrayListOf<Diary>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        val descrTxt: TextView = itemView.findViewById(R.id.descrTxt)
        val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)

        val deleteImg: ImageView = itemView.findViewById(R.id.deleteImg)
        val editImg: ImageView = itemView.findViewById(R.id.editImg)

    }

    fun addAllDiaries(newDiaryList: List<Diary>) {
        diaryList.clear()
        diaryList.addAll(newDiaryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_diary_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary = diaryList[position]

        holder.titleTxt.text = diary.title
        holder.descrTxt.text = diary.description

        val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())

        holder.dateTxt.text = dateFormat.format(diary.date)

        holder.deleteImg.setOnClickListener {
            if (holder.adapterPosition != -1) {
                deleteUpdateCallback("delete", holder.adapterPosition, diary)
            }
        }
        holder.editImg.setOnClickListener {
            if (holder.adapterPosition != -1) {
                deleteUpdateCallback("update", holder.adapterPosition, diary)
            }
        }
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }
}
