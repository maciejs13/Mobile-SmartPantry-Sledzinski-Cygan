package com.example.mobile_smart_pantry_project_iv

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val products = loadProducts(this)
        val adapter = PantryAdapter(products)

        binding.recyclerViewProducts.adapter = adapter
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)
    }
}


fun loadProducts(context: Context): List<Product> {
    val inputStream = context.resources.openRawResource(R.raw.pantry)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    return kotlinx.serialization.json.Json.decodeFromString(jsonString)
}