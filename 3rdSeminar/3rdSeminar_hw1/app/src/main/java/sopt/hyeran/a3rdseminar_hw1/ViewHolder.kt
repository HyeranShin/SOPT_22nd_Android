package sopt.hyeran.a3rdseminar_hw1

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var color : ImageView = itemView!!.findViewById(R.id.item_tv_color) as ImageView
    var numColor : TextView = itemView!!.findViewById(R.id.item_tv_num) as TextView
    var num : TextView = itemView!!.findViewById(R.id.item_tv_num) as TextView
    var type : TextView = itemView!!.findViewById(R.id.item_tv_type) as TextView
}