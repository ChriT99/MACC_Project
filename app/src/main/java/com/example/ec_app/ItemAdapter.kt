package com.example.ec_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ec_app.ItemAdapter.ItemViewHolder

class ItemAdapter(
    var itemList: List<Item>
) : RecyclerView.Adapter<ItemViewHolder>() {

    inner  class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvItem).text = itemList[position].title
            findViewById<ImageView>(R.id.ivItem).setImageResource(itemList[position].image)
        }

    }

    override fun getItemCount(): Int {
       return itemList.size
    }
}