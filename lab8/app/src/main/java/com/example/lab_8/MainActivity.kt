package com.example.lab_8

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.recyclerViewVert.layoutManager = LinearLayoutManager(this)
        val iphones = listOf(
            Pair("Iphone 10", "https://a.allegroimg.com/s600/113ecf/823996d144edb8924da9375e5bc1/APPLE-IPHONE-X-10-64GB-WYBOR-KOLOROW-SMARTFON"),
            Pair("Iphone 11", "https://store.iland.ua/media/catalog/product/i/p/iphone11-black-select-2019.png?width=270&height=270&store=default&image-type=image"),
            Pair("Iphone 12", "https://hotline.ua/img/tx/344/3442123725.jpg"),
            Pair("Iphone 13", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhUbNb7fsyXX18REE5ShD8gA4yJsv5SkNJAw&s"),
            Pair("Iphone 14", "https://scdn.comfy.ua/89fc351a-22e7-41ee-8321-f8a9356ca351/https://cdn.comfy.ua/media/catalog/product/u/a/uaua_iphone14_midnight_pdp_image_position-1a_2.jpg/w_600"),
            Pair("Iphone 15", "https://cdn.comfy.ua/media/x/img3/2601131/img3.png"),
        )

        val adapter = IphoneAdapter(iphones){ position: Int ->
            val intent = Intent(this, IphoneDetailsActivity::class.java)
            intent.putExtra("iphoneName", iphones[position].first)
            intent.putExtra("image", iphones[position].second)
            startActivity(intent)
        }

        binding.recyclerViewVert.adapter = adapter
        binding.recyclerViewHor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val iphoneNames = listOf(
            "Iphone 10",
            "Iphone 11",
            "Iphone 12",
            "Iphone 13",
            "Iphone 14",
            "Iphone 15",
        )
        val adapterSecond = HorizontalViewAdapter(iphoneNames)
        binding.recyclerViewHor.adapter = adapterSecond
    }
}