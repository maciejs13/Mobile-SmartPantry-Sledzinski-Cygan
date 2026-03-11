package com.example.mobile_smart_pantry_project_iv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_smart_pantry_project_iv.databinding.ItemProductBinding

class PantryAdapter(
    private val products: List<Product>
) : RecyclerView.Adapter<PantryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.binding.productName.text = product.nazwa
        holder.binding.productAmount.text = "${product.ilosc} ${product.jednostka}"
    }

    override fun getItemCount() = products.size
}