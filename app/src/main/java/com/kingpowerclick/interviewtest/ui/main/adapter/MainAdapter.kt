package com.kingpowerclick.interviewtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kingpowerclick.interviewtest.R
import com.kingpowerclick.interviewtest.data.model.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

interface OnItemClickListener {
    fun onItemClicked(photo: Photo?)
}

class MainAdapter(private val photos: ArrayList<Photo>, private val onItemClicked: (Photo) -> Unit) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) {
            itemView.textView.text = photo.title
            Picasso.get().load(photo.thumbnailUrl).placeholder(R.drawable.ic_placeholder).into(
                itemView.imageView
            );
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = photos[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClicked(item) }
    }



    fun addData(list: List<Photo>) {
        photos.addAll(list)
    }

}