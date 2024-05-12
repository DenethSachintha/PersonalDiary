package com.sachin.personaldiary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.personaldiary.databinding.ViewDiaryLayoutBinding
import com.sachin.personaldiary.models.Diary
import java.text.SimpleDateFormat
import java.util.Locale

class DiaryRVViewBindingAdapter(
    private val deleteUpdateCallback: (type: String, position: Int, diary: Diary) -> Unit
) :
    RecyclerView.Adapter<DiaryRVViewBindingAdapter.ViewHolder>() {

    private val diaryList = arrayListOf<Diary>()


    class ViewHolder(val viewDiaryLayoutBinding: ViewDiaryLayoutBinding) :
        RecyclerView.ViewHolder(viewDiaryLayoutBinding.root)

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
            ViewDiaryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary = diaryList[position]

        holder.viewDiaryLayoutBinding.titleTxt.text = diary.title
        holder.viewDiaryLayoutBinding.descrTxt.text = diary.description

        val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())

        holder.viewDiaryLayoutBinding.dateTxt.text = dateFormat.format(diary.date)

        holder.viewDiaryLayoutBinding.deleteImg.setOnClickListener {
            if (holder.adapterPosition != -1) {
                deleteUpdateCallback("delete", holder.adapterPosition, diary)
            }
        }
        holder.viewDiaryLayoutBinding.editImg.setOnClickListener {
            if (holder.adapterPosition != -1) {
                deleteUpdateCallback("update", holder.adapterPosition, diary)
            }
        }
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }


}
