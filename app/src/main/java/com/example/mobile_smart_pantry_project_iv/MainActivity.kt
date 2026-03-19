package com.example.mobile_smart_pantry_project_iv

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var products: MutableList<Product>
    private lateinit var allProducts: MutableList<Product>
    private lateinit var adapter: PantryAdapter

    private lateinit var binding: ActivityMainBinding
    private var selectedProductIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {

        allProducts = loadProducts(this).toMutableList()
        products = allProducts.toMutableList()
        adapter = PantryAdapter(this, products)


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerViewProducts.adapter = adapter


        binding.editTextSearch.addTextChangedListener { text ->
            val query = text.toString().lowercase()

            val filtered = allProducts.filter {
                it.nazwa.lowercase().contains(query)
            }

            products.clear()
            products.addAll(filtered)
            adapter.notifyDataSetChanged()
        }


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


        binding.button1.setOnClickListener {
            products.sortByDescending { it.ilosc }
            adapter.notifyDataSetChanged()
        }

        binding.button2.setOnClickListener {
            products.sortBy { it.ilosc }
            adapter.notifyDataSetChanged()
        }



    }
}

fun loadProducts(context: Context): List<Product> {
    val inputStream = context.resources.openRawResource(R.raw.pantry)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    return kotlinx.serialization.json.Json.decodeFromString(jsonString)
}