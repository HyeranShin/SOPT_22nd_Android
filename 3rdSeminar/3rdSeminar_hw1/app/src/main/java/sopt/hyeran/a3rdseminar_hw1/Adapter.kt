package sopt.hyeran.a3rdseminar_hw1

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Adapter(var Items : ArrayList<Item>) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int = Items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.color.setColorFilter(Items[position].color)
        holder.numColor.setTextColor(Items[position].numColor)
        holder.num.text = Items[position].num
        holder.type.text = Items[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(mainView)
    }
}