package com.example.currencyconverter.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var titles = arrayOf("1", "2")
    private var images = intArrayOf(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background)
    private var buy = arrayOf("1", "2")
    private var sale = arrayOf("1", "2")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)


        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemImage.setImageResource(images[position])
        holder.itemBuyRate.text = buy[position]
        holder.itemSaleRate.text = sale[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemBuyRate: TextView
        var itemSaleRate: TextView
        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemBuyRate = itemView.findViewById(R.id.item_buy_rate)
            itemSaleRate = itemView.findViewById(R.id.item_sale_rate)
        }
    }
}