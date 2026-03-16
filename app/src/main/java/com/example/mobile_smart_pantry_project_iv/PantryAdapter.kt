package com.example.mobile_smart_pantry_project_iv

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PantryAdapter(
    context: Context,
    val products: MutableList<Product>
) : ArrayAdapter<Product>(context, 0, products) {

    private var _selectedPosition = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_product, parent, false)

        val product = products[position]

        val nameText = itemView.findViewById<TextView>(R.id.productName)
        val amountText = itemView.findViewById<TextView>(R.id.productAmount)

        nameText.text = product.nazwa
        amountText.text = "${product.ilosc} ${product.jednostka}"

        val backgroundColor = if (product.ilosc < 6) {
            Color.RED
        }  else {
            Color.TRANSPARENT
        }
        itemView.setBackgroundColor(backgroundColor)

        return itemView
    }

    fun setSelectedPosition(position: Int) {
        _selectedPosition = position
        notifyDataSetChanged()
    }
}
