package com.example.mobile_smart_pantry_project_iv

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedProductIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val products = loadProducts(this).toMutableList()
        val adapter = PantryAdapter(this, products)

        binding.recyclerViewProducts.adapter = adapter

        binding.recyclerViewProducts.setOnItemClickListener { _, _, position, _ ->
            selectedProductIndex = position
            adapter.setSelectedPosition(position)
        }

        binding.buttonPlus.setOnClickListener {
            if (selectedProductIndex != -1) {
                val product = products[selectedProductIndex]
                products[selectedProductIndex] = product.copy(ilosc = product.ilosc + 1)
                adapter.notifyDataSetChanged()
            }
        }

        binding.buttonMinus.setOnClickListener {
            if (selectedProductIndex != -1) {
                val product = products[selectedProductIndex]
                if (product.ilosc > 0) {
                    products[selectedProductIndex] = product.copy(ilosc = product.ilosc - 1)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}

fun loadProducts(context: Context): List<Product> {
    val inputStream = context.resources.openRawResource(R.raw.pantry)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    return kotlinx.serialization.json.Json.decodeFromString(jsonString)
}