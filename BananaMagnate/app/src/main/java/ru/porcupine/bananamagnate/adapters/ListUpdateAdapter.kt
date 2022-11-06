package ru.porcupine.bananamagnate.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.porcupine.bananamagnate.R
import ru.porcupine.bananamagnate.models.UpdatesModel

class ListUpdateAdapter(private val context: Context, private val updatesArray: ArrayList<UpdatesModel>) : BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return updatesArray.size
    }

    override fun getItem(position: Int): Any {
        return updatesArray[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list_item, null, true)

            holder.name = convertView!!.findViewById(R.id.name) as TextView
            holder.price = convertView.findViewById(R.id.price) as TextView

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.name!!.text = updatesArray[position].name
        if(updatesArray[position].price==0){
            holder.price!!.text = ""
        } else holder.price!!.text = updatesArray[position].price.toString()


        return convertView
    }

    private inner class ViewHolder {

        var name: TextView? = null
        internal var price: TextView? = null

    }

}