package com.sachin.personaldiary.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sachin.personaldiary.databinding.ViewDiaryLayoutBinding
import com.sachin.personaldiary.models.Diary
import java.text.SimpleDateFormat
import java.util.Locale

class DiaryRVVBListAdapter(
    private val deleteUpdateCallback: (type: String, position: Int, diary: Diary) -> Unit,
) :
    ListAdapter<Diary, DiaryRVVBListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val viewDiaryLayoutBinding: ViewDiaryLayoutBinding) :
        RecyclerView.ViewHolder(viewDiaryLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewDiaryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary = getItem(position)

        val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())

        holder.viewDiaryLayoutBinding.dateTxt.text = dateFormat.format(diary.date)
        holder.viewDiaryLayoutBinding.titleTxt.text = diary.title
        holder.viewDiaryLayoutBinding.descrTxt.text = diary.description
        //
        val bitmap = BitmapFactory.decodeByteArray(
            diary.imageData,0,diary.imageData.size
        )
        //private val imageV = itemView.findViewById<ImageView>(R.id.imageV)
        //imageV.setImageBitmap(bitmap)
        //
        holder.viewDiaryLayoutBinding.imageV.setImageBitmap(bitmap)
        //
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
    class DiffCallback : DiffUtil.ItemCallback<Diary>() {
        override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem == newItem
        }
    }

}
